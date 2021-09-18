// Navbar transition on window scroll
$(function () {
    var navbar = document.getElementById("navbar");
    $(window).scroll(function () {
        if ($(this).scrollTop() > 80) {
            $(navbar).css("background-color", "white").css("top", 0);
            $(navbar).addClass("navbar-animate");
        } else {
            $(navbar).css("background-color", "transparent").css("top", 0);
            $(".services-nav").css("background-color", "white").css("top", 0);
        }
    })

    $("#contact_form").submit(function(event) {
        event.preventDefault();
        alert("We have received your message.Thank you!");
        $("#contact_form").trigger("reset");
    })
 })