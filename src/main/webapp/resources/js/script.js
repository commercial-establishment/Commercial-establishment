//$('table').on('change', '#city', function () {
//    alert("Rest finished successful!!!");
//});

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