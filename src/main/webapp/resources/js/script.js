function selectCity() {
    var cityName = $("#city").val();

    $.get("/admin/shops/areas", {city: cityName},
        function (data) {
            var $selectArea = $('select#area');
            $selectArea.empty();
            $selectArea.prop('disabled', false);
            $.each(data, function (key, value) {
                $selectArea.append($("<option>")
                    .val(value)
                    .html(value)
                );
            });
        });
}


function addLimitTypes() {
    $('.limitInput').each(function () {
        var name = $(this).attr("id");
        var oldVal = $(this).val();
        var newVal = name + ':' + oldVal;
        $(this).val(newVal);
    });
}