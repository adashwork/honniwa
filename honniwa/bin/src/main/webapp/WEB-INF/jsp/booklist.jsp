<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>検索結果 | 本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト</title>
  <meta name="description" content="本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト">
  <meta name="keywords" content="本庭,まちライブラリー,感想,メッセージ,寄贈">
  <meta name="viewport" content="width=960">
  <link rel="shortcut icon" type="image/x-icon" href="../common/img/favicon.png">
  <link rel="apple-touch-icon" href="../common/img/icon.png">
  <meta property="og:image" content="../common/img/icon.png">
  <link rel="stylesheet" href="../common/css/import.css">
  <link href="https://fonts.googleapis.com/earlyaccess/sawarabigothic.css" rel="stylesheet" />
  <!--[if lt IE 9]><script src="common/js/html5shiv-printshiv.js"></script><![endif]-->
</head>

<body>
	<p id="page-top">
		<a href="#"></a>
	</p>
	<!-- ▼▼▼header start▼▼▼ -->
 <header class="header">
    <h1>
      <a href="<c:url value="/web" />"><img src="../common/img/hd_logo.png" alt="本庭 ほんには" /></a>
    </h1>
    <div class="box_head clearfix bd_top01 bd_bottom02">
      <nav class="nav clearfix">
        <ul class="top_nav_lt wf-sawarabigothic">
          <li><a href="<c:url value="/web" />"><span class="icono-home icon01"></span>ホーム</a></li>
          <li><a href="http://www.adash.or.jp/apply" target="_blank">お問合せ</a></li>
          <li><a href="https://www.facebook.com/machilibraryadash" target="_blank">Facebook</a></li>
          <li><a href="http://machi-library.org/" target="_blank">まちライブラリー</a></li>
        </ul>
      </nav>
    </div>
  </header>

	<!-- ▲▲▲header end▲▲▲ -->

  <!-- ▼▼▼tittle pan start▼▼▼ -->
<div class="page_ttl"><h2>検索結果<span>search</span></h2></div>
<div class="pan">
  <ul class="clearfix">
    <li><a href="<c:url value="/web" />">home</a><span>＞</span></li>
    <li>検索結果</li>
  </ul>
  </div>
  
  <!-- ▲▲▲tittle pan end▲▲▲ -->

  <!-- ▼▼▼search_box　start▼▼▼ -->
  <div class="search_box01 bg_cl01 bd_top01">
    <div class="inner clearfix">
    <span>ジャンルから探す</span>
    <ul class="clearfix">
      <li class="bg_cl02"><a href="" class="w_txt">楽しかった</a></li>
      <li class="bg_cl02"><a href="" class="w_txt">感動した</a></li>
      <li class="bg_cl02"><a href="" class="w_txt">勉強になった</a></li>
      <li class="bg_cl02"><a href="" class="w_txt">気持ちが楽になった</a></li>
    </ul>
    </div>
  </div>
  <div class="search_box02 bg_cl02">
		<div class="inner">
			<form action="<c:url value="/web/list"/>" method="GET">
				<span>本に関する感想検索はこちら</span> <input type="search" name="searchCondition"
					placeholder="キーワードを入力"> <input type="submit" name="submit"
					value="検索">
			</form>
		</div>
  </div>
  <!-- ▲▲▲search_box end▲▲▲ -->

	<!-- ▼▼▼ contents start▼▼▼ -->
	<div class="wrap">
		<div class="page_contents">
			<ul>
				<c:forEach var="book" items="${ it.bookList }">
					<li class="search_box clearfix">
						<div class="img">
							<a href="detail?bookid=<c:out value="${ book.id }" />">
								<img src="<c:out value="${ book.bookImage }" />" alt="<c:if test ="${!empty book.bookImage }"><c:out value="${ book.title }" /></c:if>" />
							</a>
						</div>
						<div class="detail owner_datail">
							<ol class="book_datail">
								<li><a href="detail?bookid=<c:out value="${ book.id }" />">
								<c:out value="${ book.title }" /></a></li>
								<li><c:out value="${ book.author }" /></li>
							</ol>
						</div>
					</li>
				</c:forEach>

			</ul>

		</div>
	</div>
	<div></div>
	<!-- ▲▲▲contents end▲▲▲ -->



  <!-- ▼▼▼footer start▼▼▼ -->
  <div class="ft_bna bd_top01 bd_bottom01">
    <ul class="clearfix">
      <li><a href="http://www.adash.or.jp/" target="_blank"><img src="../common/img/ft_bna/ft_ban01.jpg" alt=""></a></li>
      <li><a href="http://machi-library.org/" target="_blank"><img src="../common/img/ft_bna/ft_ban03.jpg" alt=""></a></li>
      <li><a href="https://www.facebook.com/machilibraryadash/" target="_blank"><img src="../common/img/ft_bna/ft_ban_fb.jpg" alt=""></a></li>
      <li><a href="https://store.line.me/stickershop/product/1355457/ja" target="_blank"><img src="../common/img/ft_bna/ft_ban_line.jpg" alt=""></a></li>
    </ul>

  </div>
  <footer class="footer clearfix">
    <div class="ft_box clearfix">
      <p class="ft_logo">
        <a href="<c:url value="/web" />"><img src="../common/img/hd_logo.png" alt="本庭（ほんには）" /></a>
      </p>
      <ul class="ft_nav clearfix">
        <li><a href="<c:url value="/web" />"><span>■</span>ホーム</a></li>
        <li><a href="https://www.facebook.com/machilibraryadash" target="_blank"><span>■</span>お問合せ</a></li>
      </ul>
    </div>
    <p class="copy">© 2017 hA'ワーク創造館</p>
</footer>
<!-- ▲▲▲footer end▲▲▲ -->
<script src="../common/js/jquery.min.js"></script>
<script src="../common/js/scroll.js"></script>
</body>
</html>