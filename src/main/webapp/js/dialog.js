function handleRequest(xhr, status, args, varDialog) {
    if (args.validationFailed) {
        PF(varDialog).jq.effect("shake", {times: 5}, 100);
<<<<<<< HEAD
    } else {
        PF(varDialog).hide();
    }
}

function handleError(xhr, status, args, varDialog) {
    if (!args.validationFailed) {
        PF(varDialog).show();
=======
    }
    else {
        PF(varDialog).hide();
>>>>>>> 25e340a2bfb3a3f2721f9973869767c91b88cf19
    }
}
