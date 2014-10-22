<%-- 
    Document   : chatbox
    Created on : Oct 22, 2014, 10:11:56 PM
    Author     : dinhquangtrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="chatbox" class="hide">
    <div id="chat-title">
        <span style="color: rgb(111, 224, 111)">●</span> Chat (20)
    </div>
    <div id="chat-content">

    </div>
    <div id="inputtext">
        <input type="text" id="chat-input" placeholder="Nhập nội dung chat..."/>
        <input type="button" id="chat-send-button" value="Gửi"/>
    </div>
</div>
<script>
    $(function() {
        $('#chat-title').click(function() {
            $('#chatbox').toggleClass("hide");
        });
        sessionStorage['startTime'] = sessionStorage['startTime'] || parseInt(+new Date() / 1000);
        var startTime = sessionStorage['startTime'];
        var lastMsg = startTime;
        // Infinity loop to get messages
        setInterval(function() {
            $.ajax({
                url: 'message?action=list&from=' + lastMsg
            }).done(function(msg) {
                msg = JSON.parse(msg);
                if (msg.success === 1) {
                    $('#chat-content').html($('#chat-content').html() + msg.content);
                    lastMsg = msg.lastMsg;
                    
                    var c = $('#chat-content')[0];
                    //if (c.offsetHeight + c.scrollTop === c.scrollHeight) {
                        c.scrollTop = c.scrollHeight;
                    //}
                }
            });
        }, 3000);

        // Handle send message
        $('#chat-send-button').click(function() {
            if ($('#chat-input').val() === "")
                return;
            $('#chat-input').attr('disabled','disabled');
            $.ajax({
                url: 'message',
                data: {
                    action: 'add',
                    content: $('#chat-input').val()
                },
                type: 'post'
            }).done(function(msg) {
                console.log(msg);
                $('#chat-input')
                        .val('')
                        .removeAttr('disabled')
                        .focus();
            });
        });

        // Make Enter key also send message
        $('#chat-input').keydown(function(e) {
            if (e.keyCode === 13) {
                $('#chat-send-button').click();
            }
        });
    });
</script>
