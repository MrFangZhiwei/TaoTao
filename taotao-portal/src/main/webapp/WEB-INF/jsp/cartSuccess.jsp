<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html class="root61">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/css/saved_resource">
<link rel="stylesheet" type="text/css" href="/css/addtocart-album.css">
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<title>商品已成功加入购物车</title>
<link rel="stylesheet" type="text/css" href="/css/saved_resource(2)">
</head>
<body>
	<jsp:include page="commons/header.jsp" />
	<div id="o-header-2013">
		<div id="header-2013" style="display:none;"></div>
	</div>
	<div class="main">
		<div class="success-wrap">
			<div class="w" id="result">
				<div class="m succeed-box">
					<div class="mc success-cont">
						<div class="success-lcol">
							<div class="success-top">
								<b class="succ-icon"></b>
								<h3 class="ftx-02">商品已成功加入购物车！</h3>
							</div>
							<div class="p-item">
								<div class="p-img">
									<a href="https://item.jd.com/3367222.html" target="_blank"><img src="/images/58be6e50N9a113c85.jpg" clstag="pageclick|keycount|201601152|11"> </a>
								</div>
								<div class="p-info">
									<div class="p-name">
										<a href="https://item.jd.com/3367222.html" target="_blank" clstag="pageclick|keycount|201601152|2" title="联想（Lenovo）H3060 台式办公电脑整机（I3-6100 4G 500G DVD 无线网卡 蓝牙 三年上门 Win10）21.5英寸">联想（Lenovo）H3060 台式办公电脑整机（I3-6100 4G 500G DVD 无线网卡 蓝牙 三年上门 Win10）21.5英寸</a>
									</div>
									<div class="p-extra">
										<span class="txt" title="21.5英寸套机">颜色：21.5英寸套机</span><span class="txt" title="i3 4G 500G">尺码：i3 4G 500G</span><span class="txt">/ 数量：1</span>
									</div>
								</div>
								<div class="clr"></div>
							</div>
						</div>
						<div class="success-btns success-btns-new">
							<div class="success-ad">
								<a href="https://cart.jd.com/addToCart.html?rcd=1&amp;pid=3367222&amp;pc=1&amp;nr=1&amp;rid=1489471553207&amp;em=#none"></a>
							</div>
							<div class="clr"></div>
							<div>
								<a class="btn-tobback" href="javascript:history.back()" target="_blank" clstag="pageclick|keycount|201601152|3">返回继续购物</a><a class="btn-addtocart" href="/cart/cart.html" id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"><b></b>去购物车结算</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="commons/footer.jsp" />
</body>

</html>

