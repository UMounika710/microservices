<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<style>
body {
    font-family: sans-serif;
    margin: 0;
    padding: 20px;
    text-align: center;
}

header {
    margin-bottom: 20px;
}

h1 {
    font-size: 24px;
    margin-bottom: 10px;
}

.logout-button {
    color: #333;
    text-decoration: azure;
    display: flex;
    justify-content: right;
}

.app-grid {
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* Create three columns */
    gap: 20px; /* Add spacing between app links */
}
  .navbar {
            background-color: #343a40;
            color: white;
            width: 100%;
            margin-bottom: 20px;
            text-align: center;
            padding: 5px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }


.app-link {
    background-color: #add8e6;
    padding: 25px 10px;
    border-radius: 5px;
    text-decoration: none;
    color: #333;
    display: flex;
    align-items: center;
    justify-content: center;
     width: 70%;
}

.app-link:hover {
    background-color: #e5e5e5;
}
.footer {
	position: fixed;
	bottom: 0;
	width: 100%;
	background-color: #343a40; /* Adjust the color as needed */
	color: white;
	text-align: center;
	padding: 10px 0;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mt-2">
    <div class="container">
        <h2>MATERIAL INSPECTION MODULE</h2>
    </div>
      <div class="ml-auto allign-right">
            <a href="/" class="nav-link text-white">Logout</a>
        </div>
</nav>
	<header>
		<main>
		<div class="app-grid mt-5">
		    <a href="/vendor/all" class="app-link">Vendor</a>
			<a href="/plant/all" class="app-link">Plant</a>
			<a href="/material/all" class="app-link">Materials</a>
			<a href="/mtch/all" class="app-link">Material Inspection Characteristics</a>
			<a href="/ispact/all" class="app-link">Material Inspection Actuals</a>
			<a href="/lot/all" class="app-link">Inspection Lot</a>
		</div>
	</main>
	</header>
	<footer class="footer">
			<div class="container pt-2 pb-2">
				<span>&copy; 2024 Zettamine Labs Pvt Ltd. All rights
					reserved.</span>
			</div>
		</footer>
</body>
</html>
