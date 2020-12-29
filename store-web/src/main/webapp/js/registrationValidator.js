let positiveBorderColor = '#42c91a';
let negativeBorderColor = '#e03737';

window.onload = function () {
    $(".authorization-button").prop("disabled", true)

    $("#login").on('input', function () {
        validateLogin();
    })

    $("#name").on('input', function () {
        validateName();
    })

    $("#surname").on('input', function () {
        validateSurname();
    })

    $("#password").on('input', function () {
        validatePassword();
    })

    $("#email").on('input', function () {
        validateEmail();
    })

    $("#birth-date").on('input', function () {
        validateBirthDate();
    })

    $(".reg-inputs").on('input', function () {
        if (isValidLogin() && isValidName() && isValidSurname() && isValidPassword() && isValidEmail() && isValidBirthDate()) {
            $(".authorization-button").prop("disabled", false);
        } else {
            $(".authorization-button").prop("disabled", true)
        }
    })
};

function validateLogin() {
    if (isValidLogin()) {
        $("#login").css({
            'border-color': positiveBorderColor
        })
    } else {
        $("#login").css({
            'border-color': negativeBorderColor
        })
    }
}

function validateName() {
    if (isValidName()) {
        $("#name").css({
            'border-color': positiveBorderColor
        })
    } else {
        $("#name").css({
            'border-color': negativeBorderColor
        })
    }
}

function validateSurname() {
    if (isValidSurname()) {
        $("#surname").css({
            'border-color': positiveBorderColor
        })
    } else {
        $("#surname").css({
            'border-color': negativeBorderColor
        })
    }
}

function validatePassword() {
    if (isValidPassword()) {
        $("#password").css({
            'border-color': positiveBorderColor
        })
    } else {
        $("#password").css({
            'border-color': negativeBorderColor
        })
    }
}

function validateEmail() {
    if (isValidEmail()) {
        $("#email").css({
            'border-color': positiveBorderColor
        })
    } else {
        $("#email").css({
            'border-color': negativeBorderColor
        })
    }
}

function validateBirthDate() {
    if (isValidBirthDate()) {
        $("#birth-date").css({
            'border-color': positiveBorderColor
        })
    } else {
        $("#birth-date").css({
            'border-color': negativeBorderColor
        })
    }
}

function isValidBirthDate() {
    return new Date(($("#birth-date").val())).getFullYear() <= 2004 && new Date(($("#birth-date").val())).getFullYear() >= 1920;
}

function isValidPassword() {
    return $("#password").val().length > 3 && $("#password").val().length < 45;
}

function isValidEmail() {
    let emailRegexp = /^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/
    return emailRegexp.test($("#email").val());
}

function isValidName() {
    return $("#name").val().length > 1 && $("#name").val().length < 45;
}

function isValidLogin() {
    return $("#login").val().length > 1 && $("#login").val().length < 45;
}

function isValidSurname() {
    return $("#surname").val().length > 1 && $("#surname").val().length < 45;
}