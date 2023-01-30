

$(document).ready(function () {

    $('#sidebarCollapse').on('click', function () {
        console.log("clicked")
        $('#sidebar').toggleClass('active');
        $('#main-content').toggleClass('active');
    });

});




