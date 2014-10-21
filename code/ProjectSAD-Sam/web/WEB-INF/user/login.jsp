<%-- 
    Document   : login
    Created on : Oct 19, 2014, 9:23:31 PM
    Author     : dinhquangtrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<div class="box sign-in login-mask">
    <h2 class="itc-signin-header">
        Đăng nhập<br/><font size="5">bằng tài khoản CMS</font>
    </h2>

    <div id="ds_container">


        <form onsubmit="$('.btn-signin').click();return false;" name="appleConnectForm" method="post" action="">

            <input type="hidden" name="action" value="login"/>

            <font face="Lucida Grande, Geneva, Verdana, Arial, Helvetica, sans-serif" class="L12">
            <font size="2">
            <span class="dstext"></span>
            </font>
            </font><br><br>


            <table cellpadding="0" cellspacing="0" border="0" width="100%">
                <tbody>
                    <tr valign="top">
                        <td align="left"><font size="2"><font face="Geneva, Verdana, Arial, Helvetica, sans-serif" size="1"
                                                              class="G10">
                            <label for="accountname"><span class="dslabel">Tài khoản CMS</span></label>
                            <font color="#ff1102"></font></font><br>
                            <font size="2"><input size="30" autocapitalize="off" autocorrect="off" maxlength="128"
                                                  id="accountname" type="text" value=""
                                                  name="username" placeholder="Tài khoản CMS"></font></font>

                        </td>
                    </tr>

                    <tr>
                        <td><img alt="" width="273" height="5" src="img/spacer.gif"></td>
                    </tr>
                    <tr>
                        <td><img alt="" width="273" height="5" src="img/spacer.gif"></td>
                    </tr>
                    <tr>
                        <td align="left">
                            <font face="Geneva, Verdana, Arial, Helvetica, sans-serif" size="1" class="G10">
                            <label for="accountpassword"><span class="dslabel">Password</span></label>
                            </font>
                            <br>
                            <input size="30" autocapitalize="off" autocorrect="off" id="accountpassword" type="password"
                                   name="password" placeholder="Password"><a class="btn-signin">Sign In</a><input
                                   border="0" width="0" height="0" type="image" name="1.Continue"
                                   src="img/spacer.gif">
                        </td>
                    </tr>


                    <tr>
                        <td><img alt="" width="273" height="5" src="img/spacer.gif"></td>
                    </tr>
                    <tr>
                        <td><img alt="" width="273" height="5" src="img/spacer.gif"></td>
                    </tr>

                    <tr>
                        <td>
                            <table cellspacing="0" border="0" width="100%" cellpadding="0">
                                <tbody>
                                    <tr align="left">

                                        <td align="left" width="170">

                                        </td>
                                        <td width="15"><img alt="" width="15" height="5" src="img/spacer.gif">
                                        </td>


                                        <td align="left" width="88"><input border="0" alt="Sign In" type="image"
                                                                           name="1.Continue" src="img/transparent.gif"
                                                                           class="sign-in"></td>


                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="theAuxValue">
            
        </form>
        <div class="box-msg error">
            ${errorMessage}
        </div>


    </div>


    <div id="msg-container" style="margin-top: 10px; text-align: center">
        <img id="loadingImg" style="width: 28px; height: 28px;" src="img/spinner_gray_small.gif"/>
    </div>
    <script>
        var updateSubmitBtn = function() {
            if ($('#accountname').val().length > 3
                    && $('#accountpassword').val().length > 3) {
                $('.btn-signin').addClass('enabled');
            } else {
                $('.btn-signin').removeClass('enabled');
            }
        };
        $('#accountname').keyup(updateSubmitBtn);
        $('#accountpassword').keyup(updateSubmitBtn);
        $('.btn-signin').click(function() {
            if ($(this).hasClass('enabled')) {
                document.appleConnectForm.submit();
                $('.box-msg.error').html('');
                $('#loadingImg').show();
            }
        });
    </script>
</div>

<%@include file="../include/footer.jsp" %>