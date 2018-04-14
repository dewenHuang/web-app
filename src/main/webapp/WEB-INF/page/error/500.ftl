<@override name="title">服务器异常</@override>
<@override name="css">
<style>
    .error-layout{
        text-align: center;
    }

    .error-layout img{
        margin: 100px auto 50px;
        width: 334px;
        height: 514px;
    }

    @media screen and (max-width: 767px) {
        .error-layout img{
            width: 60%;
            height: auto;
        }
    }
</style>
</@override>
<@override name="content">
<div class="error-layout-wrap">
    <div class="wrapper error-layout">
        <img src="/assets/img/error/500.png"/>
    </div>
</div>
</@override>
<@extends name="include/base.ftl"/>
