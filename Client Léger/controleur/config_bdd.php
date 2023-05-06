<?php
	$serveur = "localhost"; 
	$bdd = "travelin"; 
	$user = "root"; 
	$mdp = "";
	$dsn = 'mysql:dbname=travelin;host=localhost';


	try
{
	$pdo = new PDO($dsn,$user,$mdp);
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch(PDOException $e)
{
	echo "PDO error".$e->getMessage();
	die();
}


?>