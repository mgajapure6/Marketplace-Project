<!DOCTYPE html>
<html lang="zxx">
<head>
<!-- The above 6 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keyword" content="">
<meta name="author" content="" />
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<!-- Page Title -->
<title>Sales Monitoring</title>
<!-- Main CSS -->
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/font-awesome/css/font-awesome.min.css" />
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/flag-icon/flag-icon.min.css" />
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/simple-line-icons/css/simple-line-icons.css">
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/ionicons/css/ionicons.css">
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/toastr/toastr.min.css">
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/chartist/chartist.css">
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/apex-chart/apexcharts.css">
<link type="text/css" rel="stylesheet" href="backend/assets/css/app.min.css" />
<link type="text/css" rel="stylesheet" href="backend/assets/css/style.min.css" />

<!-- new -->
<link type="text/css" rel="stylesheet" href="backend/assets/plugins/animate/animate.css" />
<link type="text/css" rel="stylesheet" href="backend/assets/css/common.css" />
<!-- end new -->
<!-- Favicon -->
<link rel="icon" href="backend/assets/images/favicon.ico" type="image/x-icon">
<style>
</style>
</head>
<body>
	<!--================================-->
	<!-- Page Container Start -->
	<!--================================-->
	<div class="page-container">
		<!--================================-->
		<!-- Page Sidebar Start -->
		<!--================================-->
		<jsp:include page="../util/sidemenu.jsp"></jsp:include>
		<!--/ Page Sidebar End -->
		<!--================================-->
		<!-- Page Content Start -->
		<!--================================-->
		<div class="page-content">
			<!--================================-->
			<!-- Page Header Start -->
			<!--================================-->
			<jsp:include page="../util/header.jsp"></jsp:include>
			<!--/ Page Header End -->
			<!--================================-->
			<!-- Page Inner Start -->
			<!--================================-->
			<div class="page-inner">
				<!-- Main Wrapper -->
				<div id="main-wrapper">
					<jsp:include page="../dashboard/dashboard.jsp"></jsp:include>
				</div>
				<!--/ Main Wrapper End -->
			</div>
			<!--/ Page Inner End -->
			<!--================================-->
			<!-- Page Footer Start -->
			<!--================================-->
			<jsp:include page="../util/footer.jsp"></jsp:include>
			<!--/ Page Footer End -->
		</div>
		<!--/ Page Content End -->
	</div>
	<!--/ Page Container End -->
	<!--================================-->
	<!-- Scroll To Top Start-->
	<!--================================-->
	<a href="#" data-click="scroll-top" class="btn-scroll-top fade">
		<i class="fa fa-arrow-up"></i>
	</a>
	<!--/ Scroll To Top End -->
	<!--================================-->
	<!-- Setting Sidebar Start -->
	<!--================================-->
	<jsp:include page="../util/setting-sidebar.jsp"></jsp:include>
	<!--/ Setting Sidebar End  -->
	<!--================================-->
	<!-- Demo Sidebar Start -->
	<!--================================-->
	<!--/ Demo Sidebar End  -->
	<!--================================-->
	<!-- Footer Script -->
	<!--================================-->
	<jsp:include page="../util/script.jsp"></jsp:include>
	<script>
		window.alert = function(title,msg){
			var dialog = bootbox.dialog({
			    title: title,
			    message: "<p>"+msg+"</p>",
			    size: 'large',
			    //className: 'rubberBand animated',
			    animate:false,
			    buttons: {
			        ok: {
			            label: '<i class="fa fa-check"></i> OK..!',
			            className: 'btn btn-oblong btn-outline-blue',
			            callback: function(){
			                console.log('OK clicked');
			            }
			        }
			    }
			});
		};
		window.confirm = function (title,message, callback) {
			var dialog = bootbox.dialog({
			    title: title,
			    message: "<p>"+message+"</p>",
			    size: 'large',
			    className: 'rubberBand animated',
			    buttons: {
			        ok: {
			            label: '<i class="fa fa-check"></i> OK..!',
			            className: 'btn btn-oblong btn-outline-blue',
			            callback: callback
			        },
			        cancel: {
			            label: '<i class="fa fa-times"></i> CANCEL..!',
			            className: 'btn btn-oblong btn-outline-secondary',
			            callback: function(){
			                console.log('cancel clicked');
			            }
			        }
			    }
			});
        };
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader("subdomain", "backend");
			var context = '${pageContext.request.contextPath}';
			var url = settings.url
			url = url.replace(context, context + '/backend');
			settings.url = url;
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			jqxhr.setRequestHeader(header, token);
		}).ajaxComplete(function(event, jqxhr, settings) {
			if (jqxhr.status === 401) {
				alert('Session has expired. Redirecting to login.');
				window.location.href = "${pageContext.request.contextPath}/";
			}
		});
		(function($) {
			$('.page-sidebar').css('z-index', '1500');
			var $window = $(window), $html = $('html');

			$window.resize(function resize() {
				if ($window.width() < 992 && $('.maskForMobileSidebar').length == 0) {
					$('body').append('<div class="maskForMobileSidebar" data-sidebar-main="mask" style="position: fixed; top: 0px; right: 0px; bottom: 0px; left: 0px; z-index: 1499; display: none; background-color: rgb(0, 0, 0); opacity: 0.5;"></div>')
					return $html.addClass('mobile');
				} else if ($window.width() >= 992) {
					$('body').find('.maskForMobileSidebar').remove();
					$html.removeClass('mobile');
				}
			}).trigger('resize');

			$('#sidebar-toggle-button').on('click', function() {

				if ($(window).width() < 992) {
					//alert('mobile')
					$('.maskForMobileSidebar').show();

				} else {
					$('.maskForMobileSidebar').hide();
				}
			});

			$('#sidebar-toggle-button-close').on('click', function() {
				$('.maskForMobileSidebar').hide();
			});

			$(document).on('click', '.maskForMobileSidebar', function() {
				$('#sidebar-toggle-button-close').click();
			});

			$('#settingSidebarTrigger').on('click', function() {
				if ($().data('sidebar-main') == 'closed') {
					$('#settingSidebar').addClass('hide');
				} else {
					$('#settingSidebar').removeClass('hide');
				}
			});

			$.get('${pageContext.request.contextPath}/getHello', function(resp) {
				console.log('get resp::', resp);
			});

			$.ajax({
				url : '${pageContext.request.contextPath}/ajaxHello',
				success : function(resp) {
					console.log('AJAX resp::', resp);
				}
			})
		})(jQuery);
	</script>
</body>
<!-- Mirrored from colorlib.net/metrical/light/index.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 10 Oct 2019 06:51:43 GMT -->
</html>