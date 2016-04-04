$(function() {
    $(function() {
        $('.parallax').parallax();

        if($('#player').length > 0) {
            var url = 'https://soundcloud.com/user-790524602/' + $('#player').attr('data') + '-1';
            SC.oEmbed(url, {
                show_user: false
            }).then(function(data) {
               $('#player').html(data.html);
            });
        }

        $('#search').keyup(function() {
            var text = $(this).val();

            $('.meeting-name').each(function(index, item) {
                if($(this).text().indexOf(text) === -1) {
                    $(this).parent().hide();
                } else {
                    $(this).parent().show();
                }
            });
        });

        $('#fromdatepicker').datepicker({
            defaultDate: "-1w",
            changeMonth: true,
            numberOfMonths: 2,
            dateFormat: 'dd.mm.yy',
            onClose: function( selectedDate ) {
                $( "#todatepicker" ).datepicker( "option", "minDate", selectedDate );
            }
        });
        $('#todatepicker').datepicker({
            defaultDate: "-1w",
            changeMonth: true,
            numberOfMonths: 2,
            dateFormat: 'dd.mm.yy',
            onClose: function( selectedDate ) {
                $( "#fromdatepicker" ).datepicker( "option", "maxDate", selectedDate );
            }
        });

        $('form.searchform').submit(function(event) {
            event.stopPropagation();
            event.preventDefault();
        });

        $('.logout-button').bind('click', function() {
            FB.getLoginStatus(function (response) {
                if(response.status === 'connected') {
                    var accessToken = response.authResponse.accessToken;
                    FB.logout(function(response) {
                        console.info("Successfully logout from facebook...");
                        location.href = "/logout";
                    });
                }
            });
        })
    });
});