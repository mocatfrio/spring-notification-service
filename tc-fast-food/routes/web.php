<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return redirect('login');
});
Route::get('/login', function () {
    return view('login');
});
Route::post('/login', 'CoreController@login');
Route::get('/device', 'CoreController@getDevice');
Route::post('/device', 'CoreController@getDevice');
Route::get('/send', 'CoreController@formNotif');
Route::post('/send', 'CoreController@createNotif');
Route::post('/send/select2', 'CoreController@select2user');
