<%@ page language="java"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-integer code="administrator.indicators.form.label.totalNumberOfAnnouncements" path="totalNumberOfAnnouncements" />
	<acme:form-integer code="administrator.indicators.form.label.totalNumberOfCompanyRecords" path="totalNumberOfCompanyRecords" />
	<acme:form-integer code="administrator.indicators.form.label.totalNumberOfInvestorRecords" path="totalNumberOfInvestorRecords" />
	<acme:form-money code="administrator.indicators.form.label.minimumRewardsOfActiveRequests" path="minimumRewardsOfActiveRequests" />
	<acme:form-money code="administrator.indicators.form.label.maximumRewardsOfActiveRequests" path="maximumRewardsOfActiveRequests" />
	<acme:form-money code="administrator.indicators.form.label.averageRewardsOfActiveRequests" path="averageRewardsOfActiveRequests" />
	<acme:form-money code="administrator.indicators.form.label.standardDeviationOfActiveRequests"
		path="standardDeviationOfActiveRequests" />
	<acme:form-money code="administrator.indicators.form.label.minimumRewardsOfActiveOffers" path="minimumRewardsOfActiveOffers" />
	<acme:form-money code="administrator.indicators.form.label.maximumRewardsOfActiveOffers" path="maximumRewardsOfActiveOffers" />
	<acme:form-money code="administrator.indicators.form.label.averageRewardsOfActiveOffers" path="averageRewardsOfActiveOffers" />
	<acme:form-money code="administrator.indicators.form.label.standardDeviationOfActiveOffers" path="standardDeviationOfActiveOffers" />
	<acme:form-double code="administrator.indicators.form.label.averageNumberOfJobsPerEmployer" path="averageNumberOfJobsPerEmployer" />
	<acme:form-double code="administrator.indicators.form.label.averageNumberOfApplicationsPerEmployer" path="averageNumberOfApplicationsPerEmployer" />
	<acme:form-double code="administrator.indicators.form.label.averageNumberOfApplicationsPerWorker" path="averageNumberOfApplicationsPerWorker" />
	
	<div class="container-fluid row">
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas1"></canvas>
		</div>
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas2"></canvas>
		</div>
	</div>
	<div class="container-fluid row">
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas3"></canvas>
		</div>
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas4"></canvas>
		</div>
	</div>
	
	<div class="container-fluid row">
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas5"></canvas>
		</div>
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas6"></canvas>
		</div>
		<div class="container-fluid col-md-8 col-lg-6">
			<canvas id="canvas7"></canvas>
		</div>
	</div>
	<br>

	<acme:form-return code="administrator.indicators.form.button.return" />


</acme:form>



<script type="text/javascript">


$(document).ready(function() {
		var data = {
	
	    labels: [ <jstl:forEach 
				    var="map" 
				    items="${companiesBySector}">
				    		 "${map.key}",
				   </jstl:forEach> 
				],
	    datasets: [{
	        label: "Companys",
	        data: [ <jstl:forEach 
				    var="map" 
				    items="${companiesBySector}">
				    		 ${map.value},
				   </jstl:forEach> ],
				   backgroundColor: 'rgba(45, 144, 221, 1)'
	    
	     }]
	}
		
	var options = {
		    legend : { display : true },
		    scales : { yAxes : [ { 
		        ticks : { 
		            suggestedMin : 0.0,	
		            suggestedMax : 1.0
		        }} ] }
		};
	
	var canvas, context;
	canvas = document.getElementById("canvas1");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "bar",
		data : data,
		options : options
	});
});

$(document).ready(function() {
	var data = {

    labels: [ <jstl:forEach 
			    var="map" 
			    items="${investorsBySector}">
			    		 "${map.key}",
			   </jstl:forEach> 
			],
    datasets: [{
         label: "Investors",
         data: [ <jstl:forEach 
 			    var="map" 
 			    items="${investorsBySector}">
 			    		 ${map.value},
 			   </jstl:forEach> ],
 			  backgroundColor: 'rgba(226, 75, 75, 1)'
      }]
}
	
var options = {
	    legend : { display : true },
	    scales : { yAxes : [ { 
	        ticks : { 
	            suggestedMin : 0.0,	
	            suggestedMax : 1.0
	        }} ] }
	};

var canvas, context;
canvas = document.getElementById("canvas2");
context = canvas.getContext("2d");
new Chart(context, {
	type : "bar",
	data : data,
	options : options
});
});

$(document).ready(function() {
	var data = {

    labels: ["Draft", "Published"],
    datasets: [{
        label: "Jobs",
        data: [ 
        	<jstl:out value="${ratioOfDraftJobs}" />,
        	<jstl:out value="${ratioOfPublishedJobs}" />
        ],
	backgroundColor: [
		'rgba(45, 144, 221, 1)', 'rgba(250, 10, 10, 1)'
    	]
     }]
}
	
	var options = {
		    legend : { display : true },
		    title : { 
		    	display : true,
		    	text : "Jobs"
		    	}
		};

	var canvas, context;
	canvas = document.getElementById("canvas3");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "pie",
		data : data,
		options : options
	});
});

$(document).ready(function() {
	var data = {

    labels: ["Pending", "Accepted", "Rejected"],
    datasets: [{
        label: "Applications",
        data: [ 
        	<jstl:out value="${ratioOfPendingApplications}" />,
        	<jstl:out value="${ratioOfAcceptedApplications}" />,
        	<jstl:out value="${ratioOfRejectedApplications}" />
        ],
	backgroundColor: [
		'rgba(45, 144, 221, 1)', 'rgba(250, 10, 10, 1)', 'rgba(205, 205, 3, 1)'
    	]
     }]
}
	
	var options = {
	    legend : { display : true },
	    title : { 
	    	display : true,
	    	text : "Applications"
	    	}
	};

	var canvas, context;
	canvas = document.getElementById("canvas4");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "pie",
		data : data,
		options : options
	});
});

$(document).ready(function() {
	var data = {

    labels: [ <jstl:forEach 
			    var="map" 
			    items="${pendingApplicationsPerDayLastFourWeeks}">
			    		 "${map.key}",
			   </jstl:forEach> 
			],
    datasets: [{
    	fill: false,
        label: "Pending applications",
        data: [ <jstl:forEach 
			    var="map" 
			    items="${pendingApplicationsPerDayLastFourWeeks}">
			    		 ${map.value},
			   </jstl:forEach> ],
		borderColor: '#fe8b36',
		backgroundColor: '#fe8b36',
		lineTension: 0
    
     }]
}
	
var options = {
		fill: false,
	    responsive: true,
	    legend : { display : true },
	    scales : { 
	    	xAxes: [ { 
	    		type: 'time',
	    		display: true
	    	}],
	    	yAxes : [ { 
	        	ticks : { 
	            	beginAtZero: true
	        },
	        display: true
	    	}]
	    },
	    title : { 
	    	display : true,
	    	text : "Pending applications per day"
	    }
	};

	var canvas, context;
	canvas = document.getElementById("canvas5");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "line",
		data : data,
		options : options
	});
});

$(document).ready(function() {
	var data = {

    labels: [ <jstl:forEach 
			    var="map" 
			    items="${acceptedApplicationsPerDayLastFourWeeks}">
			    		 "${map.key}",
			   </jstl:forEach> 
			],
    datasets: [{
    	fill: false,
        label: "Accepted applications",
        data: [ <jstl:forEach 
			    	var="map" 
			    	items="${acceptedApplicationsPerDayLastFourWeeks}">
			    		 ${map.value},
			   		</jstl:forEach> ],
		borderColor: '#fe8b36',
		backgroundColor: '#fe8b36',
    
     }]
}
	
var options = {
		fill: false,
	    responsive: true,
	    legend : { display : true },
	    scales : { 
	    	xAxes: [ { 
	    		type: 'time',
	    		display: true
	    	}],
	    	yAxes : [ { 
	        	ticks : { 
	            	beginAtZero: true
	        },
	        display: true
	    	}]
	    },
	    title : { 
	    	display : true,
	    	text : "Accepted applications per day"
	    }
	};

	var canvas, context;
	canvas = document.getElementById("canvas6");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "line",
		data : data,
		options : options
	});
});

$(document).ready(function() {
	var data = {

    labels: [ <jstl:forEach 
			    var="map" 
			    items="${rejectedApplicationsPerDayLastFourWeeks}">
			    		 "${map.key}",
			   </jstl:forEach> 
			],
    datasets: [{
    	fill: false,
        label: "Rejected applications",
        data: [ <jstl:forEach 
			    	var="map" 
			    	items="${rejectedApplicationsPerDayLastFourWeeks}">
			    		 ${map.value},
			   		</jstl:forEach> ],
		borderColor: '#fe8b36',
		backgroundColor: '#fe8b36',
    
     }]
}
	
var options = {
		fill: false,
	    responsive: true,
	    legend : { display : true },
	    scales : { 
	    	xAxes: [ { 
	    		type: 'time',
	    		display: true
	    	}],
	    	yAxes : [ { 
	        	ticks : { 
	            	beginAtZero: true
	        },
	        display: true
	    	}]
	    },
	    title : { 
	    	display : true,
	    	text : "Rejected applications per day"
	    }
	};

	var canvas, context;
	canvas = document.getElementById("canvas7");
	context = canvas.getContext("2d");
	new Chart(context, {
		type : "line",
		data : data,
		options : options
	});
});
</script>