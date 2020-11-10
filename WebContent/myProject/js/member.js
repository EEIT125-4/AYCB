
        var confirm = '✔';
        var error = '❌';
        //var regPwd = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&][A-Za-z\d@$!%*?&])$/;
        //var regEng=/.*[a-zA-Z\d\!@#$%^&*]+.*/
        var isNum = /^\d+$/;
        var isLetter = /^[a-zA-Z]+$/;
        var isMark = /^[!@#$%^&*]+$/;
        function checkName(input, output) {

            let name = document.getElementById(input).value;
            let sp = document.getElementById(output);
            let msg = "";
            let correct = true;
            sp.innerHTML = "";

            if (name != "") {

                if (name.length > 1) {

                    for (let i = 0; i < name.length; i++) {
                        let ch = name.charCodeAt(i);
                        console.log(ch);
                        if (ch < 0x4e00 || ch > 0x9fff) {
                            correct = false;
                            msg += "有非中文";
                            break;
                        }
                    }

                } else {
                    correct = false;
                    msg += "字數必須在兩個以上";
                }
            } else {
                correct = false;
                msg += "不可空白";
            }

            if (correct) {
                sp.innerHTML += confirm;
               
                sp.style.color = "green";

            } else {
                sp.style.color='red';
                sp.innerHTML += error + msg;

            }

        }

        function checkPwd(input, output) {

            let pwd = document.getElementById(input).value;
            let sp = document.getElementById(output);
            sp.innerHTML = "";
            let flag1 = false, flag2 = false, flag3 = false;
            if (pwd.length >= 6) {
                for (let i = 0; i < pwd.length; i++) {


                    if (isNum.test(pwd.charAt(i))) {
                        flag1 = true;
                    }
                    if (isLetter.test(pwd.charAt(i))) {
                        flag2 = true
                    }
                    if (isMark.test(pwd.charAt(i))) {
                        flag3 = true
                    }

                    console.log(`${pwd.charAt(i)},flag1=${flag1},flag2=${flag2},flag3=${flag3}`)

                }
                if (flag1 && flag2 && flag3) {
                    sp.innerHTML += confirm;
                    sp.style.color = "green";
                } else {
                    sp.color='red';
                    sp.innerHTML += error;
                    if (flag1 == false) {
                        sp.innerHTML += "必須有數字 "
                    }
                    if (flag2 == false) {
                        sp.innerHTML += "必須有英文 "
                    }
                    if (flag3 == false) {
                        sp.innerHTML += "必須有符號 "
                    }
                }

            } else {
                sp.color='red';
                sp.innerHTML = error + "密碼長度必須大於6";

            }

        }
        function getDaysinMonth(y, m) {
            year = parseInt(y, 10);
            month = parseInt(m, 10);
            let date = new Date(year, month, 0);
            return date.getDate();

        }

        function checkDate(input, output) {

            let date = document.getElementById(input).value;
            let sp = document.getElementById(output);
            let correct = true;
            let msg = "";
            sp.innerHTML = "";
            console.log(date);
            date = date.replace(/-/g, "/");
            let year, month, day;
            let DateString = date.split("/");
            if (DateString.length == 3) {
                year = parseInt(DateString[0], 10);
                month = parseInt(DateString[1], 0);
                day = parseInt(DateString[2], 0);
                d = new Date(year, month - 1, day);
                console.log(d);
                console.log(getDaysinMonth(year, month));
                if (getDaysinMonth(year, month) < day) {
                    correct = false;
                    msg += "日數不合法";
                }
                if (month < 1 || month > 12) {
                    correct = false;
                    msg += "月數不合法";
                }

            } else {
                correct = false;
                
                msg += "輸入長度錯誤";


            }
            if (correct) {
                sp.style.color = "green";
                sp.innerHTML += confirm;
               
            } else {
                sp.style.color='red';
                sp.innerHTML += error + msg;
            }

        }

 