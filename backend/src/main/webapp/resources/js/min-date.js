function minDate(input) {
    const today = new Date();
    let day = today.getDate();
    let month = today.getMonth() + 1;
    const year = today.getFullYear();
    let hour = today.getHours();
    let minutes = today.getMinutes();
    if (day < 10)
        day = '0' + day
    if (month < 10)
        month = '0' + month
    if (hour < 10)
        hour = '0' + hour;
    if (minutes < 10)
        minutes = '0' + minutes;
    input.min = year + '-' + month + '-' + day + 'T' + hour + ':' + minutes;
}

