    let billInput = document.getElementById('validationCustom02');
    let caseInput = document.getElementById('validationCustom01');
    let caseNumberInput = document.getElementById('validationCustom03');
    let courtTypeInput = document.getElementById('validationCustom04');
    var numericRegex = /^[0-9]+$/;

    billInput.addEventListener('input', function() {
        if (billInput.value.length > 0) {
            if (numericRegex.test(billInput.value)) {
                billInput.classList.remove('is-invalid');
                billInput.classList.add('is-valid');
            } else {
                billInput.classList.remove('is-valid');
                billInput.classList.add('is-invalid');
            }
        } else {
            // If the input is empty
            billInput.classList.remove('is-valid');
            billInput.classList.add('is-invalid');
        }
    });

    caseInput.addEventListener('input', function() {
        if (caseInput.value.length > 0) {
            caseInput.classList.remove('is-invalid');
            caseInput.classList.add('is-valid');
        } else {
            // If the input is empty
            caseInput.classList.remove('is-valid');
            caseInput.classList.add('is-invalid');
        }
    });

    caseNumberInput.addEventListener('input', function() {
        if (caseNumberInput.value.length > 0) {
            caseNumberInput.classList.remove('is-invalid');
            caseNumberInput.classList.add('is-valid');
        } else {
            // If the input is empty
            caseNumberInput.classList.remove('is-valid');
            caseNumberInput.classList.add('is-invalid');
        }
    });




    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict'

        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.querySelectorAll('.needs-validation');

        // Loop over them and prevent submission
        Array.prototype.slice.call(forms)
            .forEach(function(form) {
                form.addEventListener('submit', function(event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }

                    form.classList.add('was-validated')
                }, false)
            })
    })();

    courtTypeInput.addEventListener('change', function() {
        if (this.value) { // Assuming the first option is empty and used as the default
            this.classList.remove('is-invalid');
            this.classList.add('is-valid');
        } else {
            this.classList.remove('is-valid');
            this.classList.add('is-invalid');
        }
    });


    function checkAndSubmit() {
        let billInput = document.getElementById('validationCustom02');
        let caseInput = document.getElementById('validationCustom01');
        let caseNumberInput = document.getElementById('validationCustom03');
        let courtTypeInput = document.getElementById('validationCustom04');
        if ((caseInput.classList.contains('is-valid') && billInput.classList.contains('is-valid') && caseNumberInput.classList.contains('is-valid') && courtTypeInput.classList.contains('is-valid'))) {

            const billsGivenDTO = {
                name: document.getElementById("validationCustom01").value,
                caseIdentityNumber: document.getElementById("validationCustom03").value,
                feesBilled: document.getElementById("validationCustom02").value,
                courtType: document.getElementById("validationCustom04").value
            };

            fetch('/schedulex/billssubmitted', {
                    method: 'POST', // Specifies the method to be POST
                    headers: {
                        'Content-Type': 'application/json', // Sets the content type to JSON
                    },
                    body: JSON.stringify(billsGivenDTO) // Sends the gameInfo object as a JSON string in the request body
                })
                .then(response => {
                    // Checking if the response from the server is ok (status in the range 200-299)
                    if (response.ok) {
                        window.location.href = './billssubmitted';
                    }
                    // Parsing the JSON response body
                    if (response.status === 400) {
                        let container = document.getElementById("messageContainer");
                        let message = document.getElementById("message");
                        let messageHolder = document.getElementById("messageHolder");
                        messageHolder.className = "alert alert-info"
                        message.innerHTML = "You have already uploaded another case with the same information in this table";
                        container.style.display = "block";
                        setTimeout(() => {
                            let container = document.getElementById("messageContainer");
                            container.style.display = "none";
                        }, 5000); // 5000 milliseconds = 5 seconds


                    } else {
                        error => {
                            let container = document.getElementById("messageContainer");
                            let message = document.getElementById("message");
                            let messageHolder = document.getElementById("messageHolder");
                            messageHolder.className = "alert alert-danger"
                            message.innerHTML = "The case could not be added to the table :(";
                            container.style.display = "block";
                            setTimeout(() => {
                                let container = document.getElementById("messageContainer");
                                container.style.display = "none";
                            }, 5000); // 5000 milliseconds = 5 seconds



                            console.error('Error:', error);
                        }

                    }
                })
                .catch(error => {
                    let container = document.getElementById("messageContainer");
                    let message = document.getElementById("message");
                    let messageHolder = document.getElementById("messageHolder");
                    messageHolder.className = "alert alert-danger"
                    message.innerHTML = "The case could not be added to the table :(";
                    container.style.display = "block";
                    setTimeout(() => {
                        let container = document.getElementById("messageContainer");
                        container.style.display = "none";
                    }, 5000);



                    console.error('Error:', error);

                });




        }
    }

    function deleteBilledCase(element) {
        let id = element.getAttribute('data-id');
        fetch(`/schedulex/billssubmitted/${id}`, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = './billssubmitted';
                }
                if (response.status === 404) {
                    let container = document.getElementById("messageContainer");
                    let message = document.getElementById("message");
                    let messageHolder = document.getElementById("messageHolder");
                    messageHolder.className = "alert alert-info"
                    message.innerHTML = "The case u seek to delete does not exist in this table anymore! ";
                    container.style.display = "block";
                    setTimeout(() => {
                        let container = document.getElementById("messageContainer");
                        container.style.display = "none";
                    }, 5000); // 5000 milliseconds = 5 seconds


                } else {
                    error => {
                        let container = document.getElementById("messageContainer");
                        let message = document.getElementById("message");
                        let messageHolder = document.getElementById("messageHolder");
                        messageHolder.className = "alert alert-danger"
                        message.innerHTML = "The case could not be deleted from the table :(";
                        container.style.display = "block";
                        setTimeout(() => {
                            let container = document.getElementById("messageContainer");
                            container.style.display = "none";
                        }, 5000);
                        console.error('Error:', error);
                        //
                    }

                }

            })
            .catch(error => {
                let container = document.getElementById("messageContainer");
                let message = document.getElementById("message");
                let messageHolder = document.getElementById("messageHolder");
                messageHolder.className = "alert alert-danger"
                message.innerHTML = "The case could not be deleted from the table :(";
                container.style.display = "block";
                setTimeout(() => {
                    let container = document.getElementById("messageContainer");
                    container.style.display = "none";
                }, 5000);
                console.error('Error:', error);
                //
                console.error('There was an error deleting the case:', error);
            });

    }