<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト</title>
  <meta name="description" content="本庭（ほんには）｜まちライブラリー感想カード・メッセージサイト">
  <meta name="keywords" content="本庭,まちライブラリー,感想,メッセージ,寄贈">
  <meta name="viewport" content="width=960">
  <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/" />common/img/favicon.png">
  <link rel="apple-touch-icon" href="<c:url value="/" />common/img/icon.png">
  <meta property="og:image" content="<c:url value="/" />common/img/icon.png">
  <link rel="stylesheet" href="<c:url value="/" />common/css/import.css">
  <script src="<c:url value="/" />common/js/jquery.min.js"></script>
  <script src="<c:url value="/" />common/js/jquery.bxslider.min.js"></script>
  <script type="text/javascript">$(document).ready(function(){$('.bxslider').bxSlider({auto:true,pause:3000,mode: 'fade',speed:3000,pager:false,controls:false});});</script>
  <link href="https://fonts.googleapis.com/earlyaccess/sawarabigothic.css" rel="stylesheet" />
  <!--[if lt IE 9]><script src="common/js/html5shiv-printshiv.js"></script><![endif]-->
</head>

<body>
  <p id="page-top">
    <a href="#"></a>
  </p>

  <!-- ▼▼▼header start▼▼▼ -->
  <header class="header">
    <h1><a href="<c:url value="/web" />"><img src="<c:url value="/" />common/img/hd_logo.png" alt="本庭 ほんには" /></a></h1>
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

  <div class="top_img">
    <div class="inner clearfix">
      <div class="img">
      <ul class="bxslider">
        <li><img src="<c:url value="/" />common/img/top_img01.jpg" alt="本庭 ほんには"></li>
        <li><img src="<c:url value="/" />common/img/top_img02.jpg" alt="本庭 ほんには"></li>
      </ul>
      </div>
      <ul class="text wf-sawarabigothic">
        <li><h2>本に挟んでいる感想カードに感想・メッセージを書いて、本を使ったコミュニケーションの和を広げましょう！！</h2></li>
        <li>このサイトではA´ワーク創造館のまちライブラリーの蔵書に書かれた寄贈者からのメッセージ、本の感想・寄贈者へのメッセージをご覧いただけます。</li></ul>
    </div>
  </div>

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


	<!-- ▼▼▼ contents/// start▼▼▼ -->
	<div class="wrap">
		<div class="contents">
		<div class="top_comment">
          <table>
            <tr>
              <td class="tbl01"><span>件数</span></td>
              <td class="tbl02">感想・メッセージの現在の件数を表示しています。</td>
            </tr>
          </table>
        </div>
			<div class="cards">


				<c:forEach var="book" items="${ it }">
				<div class="card">
					<a href="<c:url value="/" />web/detail?bookid=<c:out value="${ book.id }" />"><img src="<c:out value="${ book.bookImage }" />" alt="<c:out value="${ book.title }" />" title="<c:out value="${ book.title }" />"><span><c:out value="${ book.count }" /></span></a>


				</div>
				</c:forEach>

			</div>
		</div>
	</div>

	<!-- ▲▲▲contents end▲▲▲ -->



  <!-- ▼▼▼footer start▼▼▼ -->
  <div class="ft_bna bd_top01 bd_bottom01">
    <ul class="clearfix">
      <li><a href="http://www.adash.or.jp/" target="_blank"><img src="<c:url value="/" />common/img/ft_bna/ft_ban01.jpg" alt=""></a></li>
      <li><a href="http://machi-library.org/" target="_blank"><img src="<c:url value="/" />common/img/ft_bna/ft_ban03.jpg" alt=""></a></li>
      <li><a href="https://www.facebook.com/machilibraryadash/" target="_blank"><img src="<c:url value="/" />common/img/ft_bna/ft_ban_fb.jpg" alt=""></a></li>
      <li><a href="https://store.line.me/stickershop/product/1355457/ja" target="_blank"><img src="<c:url value="/" />common/img/ft_bna/ft_ban_line.jpg" alt=""></a></li>
    </ul>

  </div>
  <footer class="footer clearfix">
    <div class="ft_box clearfix">
      <p class="ft_logo">
        <a href="<c:url value="/web" />"><img src="<c:url value="/" />common/img/hd_logo.png" alt="本庭（ほんには）" /></a>
      </p>
      <ul class="ft_nav clearfix">
        <li><a href="<c:url value="/web" />"><span>■</span>ホーム</a></li>
        <li><a href="http://www.adash.or.jp/apply" target="_blank"><span>■</span>お問合せ</a></li>
      </ul>
    </div>
    <p class="copy">© 2017 A'ワーク創造館</p>

</footer>
<!-- ▲▲▲footer end▲▲▲ -->

<script src="<c:url value="/" />common/js/scroll.js"></script>
<script src="https://unpkg.com/minigrid@3.0.6/dist/minigrid.min.js"></script>
<script>
  (function () {
    var grid;
    function init() {
      grid = new Minigrid({
        container: '.cards',
        item: '.card',
      });
      grid.mount();
    }

    // mount
    function update() {
      grid.mount();
    }

    document.addEventListener('DOMContentLoaded', init);
    window.addEventListener('resize', update);
  })();
</script>
</body>

</html>