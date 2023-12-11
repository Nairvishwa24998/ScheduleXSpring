function deleteFromEveryWhere(element) {
        let id = element.getAttribute('data-id');
        fetch(`/schedulex/removefromall/${id}`, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    window.location.href = './allcases';
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