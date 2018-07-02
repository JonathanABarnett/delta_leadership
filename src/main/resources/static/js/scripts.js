function checkPasswordMatch() {
    var newPassword = $("#newPassword");
    var confirmPassword = $("#confirmPassword");

    if (newPassword == "" && confirmPassword == "") {
        $("#checkPasswordMatch").html("");
        $("#updateUserInfoButton").prop('disabled', false);
    } else {
        if (newPassword != confirmPassword) {
            //$("#checkPasswordMatch").html("Passwords do not match.");
            //$("#updateUserInfoButton").prop('disabled', true);
        } else {
            $("#checkPasswordMatch").html("Passwords  match.");
            // $("#updateUserInfoButton").prop('disabled', false);
        }
    }
}


$(document).ready(function () {

    $('#newPassword, #confirmPassword').on('keyup', function () {
        if ($('#newPassword').val() == $('#confirmPassword').val()) {
            $('#checkPasswordMatch').html('Passwords Match!').css('color', 'green');
            $('#updateUserInfoButton').prop('disabled', false);
            console.log('#updateUserInfoButton').prop();
        } else
            $('#checkPasswordMatch').html('Passwords Do Not Match').css('color', 'red');
        $('#updateUserInfoButton').prop('disabled', true);
    });
});