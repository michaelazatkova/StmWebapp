$(function () {
    $(function () {
        $('.parallax').parallax();

        if ($('#player').length > 0) {
            var url = 'https://soundcloud.com/user-790524602/' + $('#player').attr('data') + '-' + $('#player').attr('token');
            console.info(url);
            SC.oEmbed(url, {
                show_user: false,
                sharing: false,
                buying: false,
                show_artwork: false,
                color: "008080"
            }).then(function (data) {
                $('#player').html(data.html);
            });
        }

        $(document).on('click.chip', '.chip .material-icons', function (e) {
            doFilter();
        });

        $('#search').keyup(function () {
            doFilter();
        });

        $('#fromdatepicker').datepicker({
            defaultDate: "-1w",
            changeMonth: true,
            numberOfMonths: 2,
            dateFormat: 'dd.mm.yy',
            onClose: function (selectedDate) {
                $("#todatepicker").datepicker("option", "minDate", selectedDate);
            }
        });

        $('#fromdatepicker').change(function () {
            doFilter();
        });


        $('#todatepicker').datepicker({
            defaultDate: "-1w",
            changeMonth: true,
            numberOfMonths: 2,
            dateFormat: 'dd.mm.yy',
            onClose: function (selectedDate) {
                $("#fromdatepicker").datepicker("option", "maxDate", selectedDate + 1);
            }
        });

        $('#todatepicker').change(function () {
            doFilter();
        });

        $('form.searchform').submit(function (event) {
            event.stopPropagation();
            event.preventDefault();
        });

        $('.logout-button').bind('click', function () {
            FB.getLoginStatus(function (response) {
                if (response.status === 'connected') {
                    var accessToken = response.authResponse.accessToken;
                    FB.logout(function (response) {
                        console.info("Successfully logout from facebook...");
                        location.href = "/logout";
                    });
                }
            });
        })

    });
});

function showAll() {
    $('.meeting-wrapper').show();
}

function doFilter() {
    showAll();

    var text = $('#search').val().toLowerCase();
    console.info("DO FILTER");
    $('.meeting-name').each(function (index, item) {
        if ($(this).text().toLocaleLowerCase().indexOf(text) === -1) {
            $(this).parent().hide();
        }
    });

    if ($('#fromdatepicker').val() !== '') {
        var valueDate = $('#fromdatepicker').val().split('.');
        var value = new Date(valueDate[2] + '-' + valueDate[1] + '-' + valueDate[0]);
        $('.meeting-date')
            .each(function (index, item) {
                if ($(this).parent().is(':visible')) {
                    var date = new $(item).text().split(" ")[1].split('.');
                    date = new Date(date[2] + '-' + date[1] + '-' + date[0]);
                    if (date < value) {
                        $(this).parent().hide();
                    }
                }
            });
    }

    if ($('#todatepicker').val() !== '') {
        var valueDate = $('#todatepicker').val().split('.');
        var value = new Date(valueDate[2] + '-' + valueDate[1] + '-' + valueDate[0]);
        $('.meeting-date')
            .each(function (index, item) {
                if ($(this).parent().is(':visible')) {
                    var date = new $(item).text().split(" ")[1].split('.');
                    date = new Date(date[2] + '-' + date[1] + '-' + date[0]);
                    if (date > value) {
                        $(this).parent().hide();
                    }
                }
            });
    }

    var participants = $('.participant-container').find('.chip');
    if(participants.length > 0) {
        var participantsArray = extractNamesFromChips(participants);
        $('.meeting-participant-container').each(function (index, item) {
            if ($(item).parent().is(':visible')) {
                var children = $(item).children();
                var childrenArray = extractNamesFromChips(children);

                if (!allItemsInArray(participantsArray, childrenArray)) {
                    $(this).parent().hide();
                }
            }
        });
    }
}

function extractNamesFromChips(items) {
    var result = [];

    $.each(items, function(index, item) {
        result.push($(item).find('span').text().trim());
    });

    return result;
}

function allItemsInArray(items, array) {
    var found = true;
    $.each(items, function(index, item) {
        if($.inArray(item, array) === -1) {
            found = false;
            return false;
        }
    });

    return found;
}