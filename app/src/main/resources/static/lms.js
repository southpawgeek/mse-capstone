function addAsset() {
    var title = $("#title").val();
    var isbn = $("#isbn").val();
    var callnum = $("#call-number").val();

    var mydata = {};
    if (title != "") { mydata.title = title; }
    if (isbn != "") { mydata.isbn = isbn; }
    if (callnum != "") { mydata.callNumber = callnum; }

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

function addCopy(id) {
    var mydata = {};
    mydata.assetId = id;
    mydata.status = "NEW";
    console.log(mydata);
    fetch("/api/assets/copy", {
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
            .html("Added new copy.")
            .slideDown()
            .delay(2000)
            .slideUp(1000);
            refreshAsset(id);
            return;
        }
            throw new Error("API call failed: " + data.status);
    })
        .catch((error) => {
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

function refreshAsset(id) {
    fetch("/api/assets/" + id, {
        method: 'GET'
    })
        .then(response => response.json()
            .then(data => {
                $("#asset-total-" + id).html(data.count.stats.total);
        }))
}

function refreshAssets() {
    fetch("/api/assets")
        .then(response => response.json()
            .then(data => {
                $("#assets").empty();
                console.log(data);
                $.each(data, function (key) {
                    copies_total = data[key].count.stats.total;
                    var row = "<tr id='asset-" + data[key].id + "' class='asset-copy' data-asset-id='" + data[key].id + "'><td>" + data[key].title + "</td> <td>" + data[key].isbn + "</td> <td>" + data[key].callNumber + "</td><td><span class='asset-copies'>" + copies_total + "</span><button class='add-copy' value='" + data[key].id + "'>+</i></button></td>";
                    $("#assets").append(row);
                    $("#asset-" + data[key].id).delegate('button', 'click', function () { addCopy(data[key].id); });
                })
            })
    )
    return;
}

function viewAsset(id) {
    // cleanup prior data
    $("#asset-modal-copies").empty();
    $("#asset-modal-title").html('');

    fetch("/api/assets/" + id, {
        method: 'GET'
    })
        .then(response => response.json()
            .then(data => {
                console.log(data);
                $("#full-modal").fadeIn(250);
                $("#asset-modal-title").html(data.title);
                $("#asset-modal-isbn").html(data.isbn);
                let stats = data.count.stats;
                let copies = data.count.copies;

                $("#asset-modal-total").html(stats.total);
                $.each(copies, function(key) {
                    $("#asset-modal-copies").append("<li id='asset-copy-" + copies[key].id + "' class='asset-copy-item'><span class='asset-copy-status'>" + copies[key].status + "</span><button class='asset-copy-button' data-copy-id='" + copies[key].id + "'>Reserve this copy</button></li>");
                })
            }))
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
        .then(response => response.json()
            .then(data => {
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

    $(".add-copy").click(function () {
        addCopy($(this).val());
    })

    $(".asset-item").click(function() {
        viewAsset($(this).attr('data-asset-id'));
    })

    $("#full-modal-close").click(function() {
        closeModal();
    })

    $(document).keyup(function(e){
        if(e.keyCode === 27) {
            closeModal();
        }
    })

    function closeModal() {
        $("#full-modal").fadeOut(250);
    }
});