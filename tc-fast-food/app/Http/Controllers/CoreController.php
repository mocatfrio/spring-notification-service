<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

class CoreController extends Controller
{
    //

    public function login(Request $request){
        //return $request->input('password');
        $url = 'http://128.199.210.218:7001/token';
        $data = [
                    'email' => $request->input('email'),
                    'password' => $request->input('password'),
                ];
        $response = $this->callAPI("POST", $url, $data);
        $response = (string) $response;
        $res = json_decode($response);
        //return json_encode($res->value);
        
        $request->session()->put('jwt', $res->value);
        return redirect('/send');

        // $client = new \GuzzleHttp\Client();
        // $res = $client->request('POST', 'http://128.199.210.218:7001/token', [
        //     'headers' => [
        //         'Accept' => 'application/json',
        //         'Content-type' => 'application/json'
        //     ],
        //     'form_params' => [
        //         'email' => $request->input('email'),
        //         'password' => $request->input('123'),
        //     ],
        //     // 'debug' => true,
        // ]);
        
        // echo $res->getStatusCode();
        // // 200
        // echo $res->getHeader('content-type');
        // // 'application/json; charset=utf8'
        // echo $res->getBody();
        // return "";
    }

    public function createNotif(Request $request){
        $title = $request->input('title');
        $body = $request->input('body');
        $userIds = $request->input('userIds');
        $userIds = array_map('intval', $userIds);
        $list = "";
        foreach($userIds as $userId){
            $list = $list . "" . $userId . ", ";
        }
        $list = substr($list, 0, -2);


        $request_headers = array();
        $request_headers[] = 'Authorization: '. $request->session()->get('jwt');
        $request_headers[] = 'Content-Type: application/json';

        $data = [
            'title' => $title,
            'body' => $body,
            'userIds' => $list,
        ]; 
        $data = (string) json_encode($data);
        // return $data;
        $url = "https://fp-notification-api.herokuapp.com/notification/user?title=". $title . "&body=".$body . "&userIds=".$list;
        echo $url;
        $response = $this->callAPI("POST", $url, false, $request_headers);
        
        $response = (string) $response;
        $res = json_decode($response);
        return redirect('device');
    }
    public function formNotif(Request $request){
        
        // return json_encode($this->createDevice($request->session()->get('jwt')));

        $all_devices = $this->getAllDevice($request->session()->get('jwt'), "https://fp-notification-api.herokuapp.com/device");

        
        $available_devices = array();
        $count = 0;
        foreach($all_devices as $device){
            $user_data = $this->getUserDetail($device->userId, $request->session()->get('jwt'));
            
            if($user_data){
                $device->name = $user_data->name;
                $available_devices[$count] = $device;
                $count++;
            }
        }
        $data['devices'] = $available_devices;
        return view('send', $data);
    }



    function createDevice($jwt){
        $request_headers = array();
        $request_headers[] = 'Authorization: '. $jwt;
        $request_headers[] = 'Content-Type: application/json';

        $data = [
            'token' => "FC57E66B889917A452AE7F72CF0B89074902A4AA719FA3E5CC345C6D2D0434FD",
            'type' => "ANDROID",
            'userId' => 40,
        ]; 
        $data = (string) json_encode($data);

        $response = $this->callAPI("POST", "https://fp-notification-api.herokuapp.com/device", $data, $request_headers);
        $response = (string) $response;
        $res = json_decode($response);
        return $res;
    }
    public function getUserDetail($id, $jwt){
        $request_headers = array();
        $request_headers[] = 'Token: '. $jwt;
        $response = $this->callAPI("GET", "http://128.199.210.218:7001/user/".$id, false, $request_headers);
        $response = (string) $response;
        $res = json_decode($response);
        return $res;
    }

    public function getAllDevice($jwt, $url){
        $request_headers = array();
        $request_headers[] = 'Authorization: '. $jwt;
        $response = $this->callAPI("GET", $url, false, $request_headers);
        $response = (string) $response;
        $res = json_decode($response);
        return $res;
    }

    public function select2user(Request $request){

        return "asd";
    }
    public function getDevice(Request $request){
        $jwt = $request->session()->get('jwt');
        $users = $this->getAllUser($jwt);
        $contain = "?";
        $type = $request->input('type');
        $userId = $request->input('userId');
        if(!empty($type))
            $contain = $contain . "type=".$type."&";
        if(!empty($userId))
            $contain = $contain . "userId=".$userId."&";
        $url = "https://fp-notification-api.herokuapp.com/device".$contain;
        $all_devices = $this->getAllDevice($jwt, $url);
        
        $available_devices = array();
        $count = 0;
        foreach($all_devices as $device){
            $user_data = $this->getUserDetail($device->userId, $request->session()->get('jwt'));
            
            if($user_data){
                $device->name = $user_data->name;
                $device->username = $user_data->username;
                $device->role = $user_data->role;
                $device->email = $user_data->email;
                $device->phone_number = $user_data->phone_number;
                $available_devices[$count] = $device;
                $count++;
            }
        }
        $data['devices'] = $available_devices;
        $data['count'] = 1;
        $data['users'] = $users;
        // return json_encode($data);
        return view('device', $data);
        
        return json_encode($res);
    }

    function getAllUser($jwt){
        $request_headers = array();
        $request_headers[] = 'Token: '. $jwt;
        $response = $this->callAPI("GET", "http://128.199.210.218:7001/user", false, $request_headers);
        $response = (string) $response;
        $res = json_decode($response);
        return $res;
    }

    function callAPI($method, $url, $data = false, $headers = false)
    {
        $curl = curl_init();
        if($headers)
            curl_setopt($curl, CURLOPT_HTTPHEADER, $headers);
        switch ($method)
        {
            case "POST":
                curl_setopt($curl, CURLOPT_POST, 1);

                if ($data)
                    curl_setopt($curl, CURLOPT_POSTFIELDS, $data);
                break;
            case "PUT":
                curl_setopt($curl, CURLOPT_PUT, 1);
                break;
            default:
                if ($data)
                    $url = sprintf("%s?%s", $url, http_build_query($data));
        }

        // Optional Authentication:
        curl_setopt($curl, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
        curl_setopt($curl, CURLOPT_USERPWD, "username:password");

        curl_setopt($curl, CURLOPT_URL, $url);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);

        $result = curl_exec($curl);

        curl_close($curl);

        return $result;
    }
}
