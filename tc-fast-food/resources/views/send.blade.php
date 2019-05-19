<!doctype html>
<html lang="en" class="no-focus">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

        <title>TC Fast Food &bullet; ITS</title>

        <meta name="description" content="myITS SSO">
        <meta name="author" content="DPTSI ITS">
        <meta name="robots" content="noindex, nofollow">


        <!-- Open Graph Meta -->
        <meta property="og:title" content="Codebase - Bootstrap 4 Admin Template &amp; UI Framework">
        <meta property="og:site_name" content="Codebase">
        <meta property="og:description" content="Codebase - Bootstrap 4 Admin Template &amp; UI Framework created by pixelcave and published on Themeforest">
        <meta property="og:type" content="website">
        <meta property="og:url" content="">
        <meta property="og:image" content="">
         <!-- END Graph Meta -->

        <!-- Icons -->
        <!-- The following icons can be replaced with your own, they are used by desktop and mobile browsers -->
        <link rel="shortcut icon" href="{{ asset('assets/media/favicons/favicon-web.png') }}">
        <link rel="icon" type="image/png" sizes="192x192" href="{{ url('assets/media/favicons/favicon-192x192.png') }}">
        <!-- END Icons -->

        <!-- Stylesheets -->

        

        <!-- Page JS Plugins CSS -->
        <link rel="stylesheet" href="{{ asset('assets/js/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css') }}">
        <link rel="stylesheet" href="{{ asset('assets/js/plugins/select2/css/select2.min.css') }}">
        <link rel="stylesheet" href="{{ asset('assets/js/plugins/ion-rangeslider/css/ion.rangeSlider.css') }}">
        <link rel="stylesheet" href="{{ asset('assets/js/plugins/datatables/dataTables.bootstrap4.css') }}">

        <!-- Fonts and Codebase framework -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Muli:300,400,400i,600,700">
        <link rel="stylesheet" id="css-main" href="{{ url('assets/css/codebase.min.css') }}">


        <!-- You can include a specific file from css/themes/ folder to alter the default color theme of the template. eg: -->
        <!-- <link rel="stylesheet" id="css-theme" href="assets/css/themes/flat.min.css"> -->
        <!-- END Stylesheets -->

        <style>
        </style>
    </head>
    <body>
       
        
        <div id="page-container" class="sidebar-o enable-page-overlay side-scroll page-header-fixed page-header-modern main-content-boxed">

            <!-- Sidebar -->
            <nav id="sidebar">
                <!-- Sidebar Content -->
                <div class="sidebar-content">
                    <!-- Side Header -->
                    <div class="content-header content-header-fullrow px-15">
                        <!-- Mini Mode -->
                        <div class="content-header-section sidebar-mini-visible-b">
                            <!-- Logo -->
                            <span class="content-header-item font-w700 font-size-l float-left animated fadeIn">
                                <span class="font-size-l text-dual-primary-dark">TC </span><span class="font-size-l text-primary">Fast Food</span>
                            </span>
                            <!-- END Logo -->
                        </div>
                        <!-- END Mini Mode -->

                        <!-- Normal Mode -->
                        <div class="content-header-section text-center align-parent sidebar-mini-hidden">
                            <!-- Close Sidebar, Visible only on mobile screens -->
                            <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                            <button type="button" class="btn btn-circle btn-dual-secondary d-lg-none align-v-r" data-toggle="layout" data-action="sidebar_close">
                                <i class="fa fa-times text-danger"></i>
                            </button>
                            <!-- END Close Sidebar -->

                            <!-- Logo -->
                            <div class="content-header-item">
                                <a class="link-effect font-w700" href="{{ url('push') }}">
                                    <span class="font-size-l text-dual-primary-dark">TC </span><span class="font-size-l text-primary">Fast Food</span>
                                </a>
                            </div>
                            <!-- END Logo -->
                        </div>
                        <!-- END Normal Mode -->
                    </div>
                    <!-- END Side Header -->

                    <!-- Side Navigation -->
                    <div class="content-side content-side-full">
                        <ul class="nav-main">
                           
                            <li class="nav-main-heading"><span class="sidebar-mini-visible">MG</span><span class="sidebar-mini-hidden">Notifikasi</span></li>
                            <li>
                                <a href="{{ url('send') }}"><i class="fa fa-send-o"></i><span class="sidebar-mini-hide">Kirim Notifikasi</span></a>
                            </li>
                            
                            <li class="nav-main-heading"><span class="sidebar-mini-visible">ST</span><span class="sidebar-mini-hidden">Device</span></li>
                            <li>
                                <a href="{{ url('device') }}"><i class="fa fa-building-o"></i><span class="sidebar-mini-hide">Device</span></a>
                            </li>
                            
                            
                            
                        </ul>
                    </div>
                    <!-- END Side Navigation -->
                </div>
                <!-- Sidebar Content -->
            </nav>
            <!-- END Sidebar -->

            <!-- Header -->
            <header id="page-header">
                <!-- Header Content -->
                <div class="content-header">
                    <!-- Left Section -->
                    <div class="content-header-section">
                        <!-- Toggle Sidebar -->
                        <!-- Layout API, functionality initialized in Template._uiApiLayout() -->
                        <button type="button" class="btn btn-circle btn-dual-secondary d-lg-none" data-toggle="layout" data-action="sidebar_toggle">
                            <i class="fa fa-navicon"></i>
                        </button>
                        <!-- END Toggle Sidebar -->

                    </div>
                    <!-- END Left Section -->

                    <!-- Right Section -->
                    <div class="content-header-section">
                        <!-- User Dropdown -->
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-rounded btn-dual-secondary" id="page-header-user-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fa fa-user-md d-sm-none"></i>
                                <span class="d-none d-sm-inline-block">Rizky Januar Akbar</span>
                                <i class="fa fa-angle-down ml-5"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-right min-width-200" aria-labelledby="page-header-user-dropdown">
                                <a class="dropdown-item" href="javascript:void(0)">
                                    <i class="fa fa-pencil mr-5"></i> Profile
                                </a>
                                <a class="dropdown-item" href="javascript:void(0)">
                                    <i class="fa fa-cog mr-5"></i> Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="javascript:void(0)">
                                    <i class="fa fa-unlock-alt mr-5"></i> Sign Out
                                </a>
                            </div>
                        </div>
                        <!-- END User Dropdown -->

                    </div>
                    <!-- END Right Section -->
                </div>
                <!-- END Header Content -->

                <!-- Header Loader -->
                <div id="page-header-loader" class="overlay-header bg-primary">
                    <div class="content-header content-header-fullrow text-center">
                        <div class="content-header-item">
                            <i class="fa fa-sun-o fa-spin text-white"></i>
                        </div>
                    </div>
                </div>
                <!-- END Header Loader -->
            </header>
            <!-- END Header -->

            <!-- Main Container -->
            <main id="main-container">
                <div class="content">
                    <h2 class="content-heading">Kirim Notifikasi</h2>
                    
                    <div class="row">
                        <div class="col-md-12">
                            
                            <div class="block">
                                    <!-- Default 
                                <div class="block-header block-header-default">
                                    <h3 class="block-title">Form</h3>
                                    <div class="block-options">
                                        <button type="button" class="btn-block-option">
                                            <i class="si si-wrench"></i>
                                        </button>
                                    </div>
                                </div> 
                                Elements -->
                                <div class="block-content">
                                    <form action="{{url('send')}}" method="post" enctype="multipart/form-data">
                                        
                                        <div class="form-group row">
                                            <label class="col-12" for="example-select2-multiple">Tujuan</label>
                                            <div class="col-lg-12">
                                                <select class="js-select2 form-control" id="userIds[]" name="userIds[]" style="width: 100%;" data-placeholder="Pilih" multiple>
                                                    <option></option><!-- Required for data-placeholder attribute to work with Select2 plugin -->
                                                    @foreach($devices as $device)
                                                    <option value="{{$device->userId}}">{{$device->name}}</option>
                                                    @endforeach
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-12" for="example-text-input">Judul*</label>
                                            <div class="col-12">
                                                <input required type="text" class="form-control" id="title" name="title" placeholder="Masukkan Judul..">
                                                <!-- <div class="form-text text-muted">Further info about this input</div> -->
                                            </div>
                                        </div>
                                        
                                        <div class="form-group row">
                                            <label class="col-12" for="example-textarea-input">Konten</label>
                                            <div class="col-12">
                                                <textarea class="form-control" autocomplete="off" id="body" name="body" rows="6" placeholder="Masukkan Konten.."></textarea>
                                            </div>
                                        </div>
                                        <div class="form-group row"></div>
                                    
                                        <div class="form-group row"></div>
                                        
                                        
                                        <div class="form-group row"></div>
                                        <div class="form-group row">
                                            <div class="col-12">
                                                <div class="form-group">
                                                    <button type="submit" id="submit" class="btn btn-success mr-5 mb-5 float-right">
                                                        <i class="fa fa-send mr-5"></i>Kirim
                                                    </button>
                                                </div>
                                            </div>
                                            
                                        </div>
                                        
                                        
                                        <div class="form-group row"></div>
                                    </form>
                                </div>
                            </div>
                            <!-- END Default Elements -->
                        </div>
                    </div>
                                        
                                        <!-- END Bootstrap Design -->
                </div>

            </main>
            <!-- END Main Container -->

            <!-- Footer -->
            <footer id="page-footer" class="opacity-0">
                <div class="content py-20 font-size-xs clearfix">
                    <div class="float-left">
                        Copyright &copy; 2019 Institut Teknologi Sepuluh Nopember
                    </div>
                </div>
            </footer>
            <!-- END Footer -->
        </div>
        <!-- END Page Container -->

        <!--
            Codebase JS Core

            Vital libraries and plugins used in all pages. You can choose to not include this file if you would like
            to handle those dependencies through webpack. Please check out assets/_es6/main/bootstrap.js for more info.

            If you like, you could also include them separately directly from the assets/js/core folder in the following
            order. That can come in handy if you would like to include a few of them (eg jQuery) from a CDN.

            assets/js/core/jquery.min.js
            assets/js/core/bootstrap.bundle.min.js
            assets/js/core/simplebar.min.js
            assets/js/core/jquery-scrollLock.min.js
            assets/js/core/jquery.appear.min.js
            assets/js/core/jquery.countTo.min.js
            assets/js/core/js.cookie.min.js
        -->
        <script src="{{ url('assets/js/codebase.core.min.js') }}"></script>

        <!--
            Codebase JS

            Custom functionality including Blocks/Layout API as well as other vital and optional helpers
            webpack is putting everything together at assets/_es6/main/app.js
        -->
        <script src="{{ url('assets/js/codebase.app.min.js') }}"></script>


    

        <!-- Page JS Plugins -->
        <script src="{{ url('assets/js/plugins/moment/moment.min.js') }}"></script>
        <script src="{{ url('assets/js/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js') }}"></script>
        <script src="{{ url('assets/js/plugins/select2/js/select2.full.min.js') }}"></script>

        <script>jQuery(function(){ Codebase.helpers(['datepicker', 'colorpicker', 'maxlength', 'select2', 'masked-inputs', 'rangeslider', 'tags-inputs']); });</script>
        
        <script type="text/javascript">
            $(function(){
                $('#user_name').select2({
                    minimumInputLength: 0,
                    allowClear: true,
                    placeholder: 'Pilih',
                    ajax: {
                        dataType: 'json',
                        url: '{{ url('send/select2') }}',
                        delay: 1000,
                        data: function(params) {
                            return {
                            search: params.term
                            }
                        },
                        processResults: function (data, page) {
                        return {
                            results: data
                        };
                        },
                    }
                });
            });
        </script>


        
        
        


        
    </body>
</html>