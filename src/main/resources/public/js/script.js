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

    $(".subscribe-form").submit(function(event) {
        event.preventDefault();
        alert("Thank you for subscribing!You'll be receiving latest updates regularly.");
        $(".subscribe-form").trigger("reset");
    })

    $("#viewDropdown").click(function() {
        $("#dropdown1").slideToggle();
        $("#dropdown2").slideUp();
    })

    $("#createDropdown").click(function() {
        $("#dropdown2").slideToggle();
        $("#dropdown1").slideUp();
    })

    $("#navbar-toggler-icon").click(function() {
        $("#navbarSupportedContent").slideToggle();
    })
 })