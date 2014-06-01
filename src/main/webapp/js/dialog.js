function handleRequest(xhr, status, args, varDialog) {
    if (args.validationFailed) {
        PF(varDialog).jq.effect("shake", {times: 5}, 100);
    }
    else {
        PF(varDialog).hide();
    }
}
