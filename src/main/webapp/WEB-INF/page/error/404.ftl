<@override name="title">未找到此页内容</@override>
<@override name="css">
<style>
    .error-layout{
        text-align: center;
    }

    .error-layout img{
        margin: 150px auto 100px;
        width: 531px;
        height: 288px;
    }

    @media screen and (max-width: 767px) {
        .error-layout img{
            width: 70%;
            height: auto;
        }
    }
</style>
</@override>
<@override name="content">
<div class="error-layout-wrap">
    <div class="wrapper error-layout">
        <img src="/assets/img/error/404.png"/>
    </div>
</div>
</@override>
<@extends name="include/base.ftl"/>