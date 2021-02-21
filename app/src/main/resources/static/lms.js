/* jslint browser: true, es6: true, latedef: nofunc, global $ */
    function addAsset() {
        var title = $("#title").val();
        var isbn = $("#isbn").val();
        var callnum = $("#call-number").val();

        var mydata = {};
        if (title != "") { mydata.title = title; }
        if (isbn != "") { mydata.isbn = isbn; }
        if (callnum != "") { mydata.callNumber = callnum; }

        console.log(mydata);
        fetch("/api/assets", {
            method: 'POST',
            headers: { 'Content-type': 'application/json' },
            body: JSON.stringify(mydata)
        })
            .then(data => {
                console.log(data);
                if (data.ok) {
                $("#message")
                    .removeClass()
                    .addClass("good")
                    .html("Added your book: " + title)
                    .slideDown()
                    .delay(2000)
                    .slideUp(1000);
                    refreshAssets();
                    return;
                }
                throw new Error("API call failed: " + data.status);
        })
            .catch((error) => {
                console.error('BAD', error);
                $("#message")
                    .removeClass()
                    .addClass("bad")
                    .html(error)
                    .slideDown()
                    .delay(2000)
                    .slideUp(1000);
        })
        return;
    }

function refreshAssets() {
    fetch("/api/assets")
        .then(response => response.json().then(data => {
            $("#assets").empty();
            $.each(data, function (key) {
                $("#assets").append(
                    "<tr><td>" + data[key].title + "</td> <td>" + data[key].isbn + "</td> <td>" + data[key].callNumber + "</td></tr>"
                )
            });
        }))
        return;
}

$(document).ready(function () {
    /* clicky function for button */
    $("#add-asset").click(function () {
        addAsset();
    })
});