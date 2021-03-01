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

    function addUser() {
        var username = $("#username").val();
        var password = $("#password-hash").val();
        var email = $("#email").val();
        var first = $("#first-name").val();
        var middle = $("#middle-name").val();
        var last = $("#last-name").val();
        var user_type = $("input[type='radio'][name='user-type']:checked").val();

        var mydata = {};
        mydata.username = username;
        mydata.passwordHash = password;
        mydata.emailAddress = email;
        mydata.firstName = first;
        mydata.middleName = middle;
        mydata.lastName = last;
        mydata.userType = user_type;

    /* this is really not something we'd do in production */
        mydata.patronId = user_type + Math.floor(Math.random() * 1000000);

        console.log(mydata);
        fetch("/api/users", {
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
                    .html("Added new user: " + username)
                    .slideDown()
                    .delay(2000)
                    .slideUp(1000);
                    refreshUsers();
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

function refreshUsers() {
    fetch("/api/users")
        .then(response => response.json().then(data => {
            $("#users").empty();
            $.each(data, function (key) {
                if (data[key].lastName != "") { data[key].lastName += ", "}
                $("#users").append(
                    "<tr><td>" + data[key].id + "</td> <td>" + data[key].username + "</td> <td>" + data[key].patronId + "</td><td>" + data[key].lastName + data[key].firstName + " " + data[key].middleName + "</td></tr>"
                )
            });
        }))
        return;
}

$(document).ready(function () {
    /* clicky function hooks for buttons */
    $("#add-asset").click(function () {
        addAsset();
    })

    $("#add-user").click(function () {
        addUser();
    })
});