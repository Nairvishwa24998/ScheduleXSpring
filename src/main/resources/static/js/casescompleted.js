    let caseInput = document.getElementById('validationCustom01');
    let caseNumberInput = document.getElementById('validationCustom03');
    let courtTypeInput = document.getElementById('validationCustom04');
    var numericRegex = /^[0-9]+$/;


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
        let caseInput = document.getElementById('validationCustom01');
        let caseNumberInput = document.getElementById('validationCustom03');
        let courtTypeInput = document.getElementById('validationCustom04');
        if ((caseInput.classList.contains('is-valid') && caseNumberInput.classList.contains('is-valid') && courtTypeInput.classList.contains('is-valid'))) {

            const casesCompletedDTO = {
                name: document.getElementById("validationCustom01").value,
                caseIdentityNumber: document.getElementById("validationCustom03").value,
                courtType: document.getElementById("validationCustom04").value
            };

            fetch('/schedulex/casescompleted', {
                    method: 'POST', // Specifies the method to be POST
                    headers: {
                        'Content-Type': 'application/json', // Sets the content type to JSON
                    },
                    body: JSON.stringify(casesCompletedDTO) // Sends the gameInfo object as a JSON string in the request body
                })
                .then(response => {
                    // Checking if the response from the server is ok (status in the range 200-299)
                    if (response.ok) {
                        window.location.href = './casescompleted';
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
                            //                                 Handle errors (e.g., show user-friendly message)
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
                    }, 5000); // 5000 milliseconds = 5 seconds



                    console.error('Error:', error);
                    //                                 Handle errors (e.g., show user-friendly message)
                });




        }
    }

    function deleteCompletedCase(element) {
        let id = element.getAttribute('data-id');
        fetch(`/schedulex/casescompleted/${id}`, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = './casescompleted';
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
                        }, 5000); // 5000 milliseconds = 5 seconds
                        console.error('Error:', error);
                        //                                 Handle errors (e.g., show user-friendly message)
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
                }, 5000); // 5000 milliseconds = 5 seconds
                console.error('Error:', error);
                //                                 Handle errors (e.g., show user-friendly message)
                console.error('There was an error deleting the case:', error);
            });

    }