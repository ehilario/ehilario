/**
 * Created by ehilario on 4/8/2017.
 */
$(document).ready(function () {

    $("#display-form").submit(function (event) {
        event.preventDefault();
        fire_ajax_submit();
    });

});

function fire_ajax_submit() {

    var $start = $("#start").val();
    var $end = $("#end").val();
    var $range = {
        start: $("#start").val(),
        end: $("#end").val()
    }


    $("#btn-multiple").prop("disabled", true);

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/get",
        data: $range.valueOf(),
        dataType: 'json',
        cache: false,
        timeout: 600000,


        success: function (data) {

            var json = "<h4>Raw Json</h4><pre>"
                + JSON.stringify(data,null,2) + "</pre>";
            $('#json-output').html(json);

            var strTable;
            strTable = '';
            strTable+= '<table border="1" class="table table-hover tbldata" style="padding:2px">';
            strTable+= '<tr>';
            strTable+= '<th >multiple</th>';
            strTable+= '<th >converted</th>';
            strTable+= '</tr>';
            $.each(data, function(index, item){
                d=data[index]['id'];
                strTable+= '<tr>';
                strTable+= '<td>' + index +'</td>';
                strTable+= '<td>' + item +'</td>';
                strTable+= '</tr>';
            });
            strTable+= '</table>';

            $('#tbl').html(strTable);
        },
        error: function (e) {
            $("#tbl tbody tr").remove();
            var json = "<h4>Raw Error Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#json-output').html(json);

            alert('Error processing request')
        }

    });
    $("#btn-multiple").prop("disabled", false);
}