var ajaxUrl = "ajax/meals/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function filterMeals() {
    var form = $("#filter");
    $.ajax({
        type: "GET",
        url: "ajax/meals/filter",
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered");
        }
    });
}

function resetForm() {
    $('#filter').trigger( 'reset' );
}


















