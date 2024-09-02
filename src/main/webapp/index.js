$(document).ready(
	function() {
		$("#myInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			console.log($(this).text().toLowerCase());
			console.log($(this).text().toLowerCase().indexOf(value) > -1);
			$("#myTable tr").filter(function() { $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1) });

		});
	}
);

document.addEventListener('DOMContentLoaded', function() {
    const delay = 4000;

    setTimeout(function() {
        const alertElement = document.getElementById('allAlerts');
        if (alertElement) {
            alertElement.style.display = 'none';
        }
    }, delay);
});


document.addEventListener('DOMContentLoaded', function() {
    var alertElement = document.getElementById('allAlerts');
    if (alertElement) {
        var alertText = alertElement.querySelector('strong').textContent.trim();
        if (alertText === '') {
            alertElement.style.display = 'none';
        }
    }
});






