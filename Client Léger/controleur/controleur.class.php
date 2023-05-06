<?php 
	//controle des données avant injection ou après extraction.
	require_once ("modele/modele.class.php");

	class Controleur {
		private $unModele ; //instance de la classe Modele 

		public function  __construct($serveur, $bdd, $user, $mdp){
			//instanciation de la classe Modele 
			$this->unModele= new Modele($serveur, $bdd, $user, $mdp);
		}

		// login
		public function verifConnexion ($email, $mdp)
		{
			return $this->unModele->verifConnexion($email, $mdp); 
		}

		// recup destinations
		public function	getDestinations()
		{
			return $this->unModele->getDestinations(); 
		}

		public function getTransports()
		{
			return $this->unModele->getTransports(); 
		}

		public function getLogements()
		{
			return $this->unModele->getLogements(); 
		}

		public function getDestinationName()
		{
			return $this->unModele->getDestinationName(); 
		}

		public function getDestinationPrix()
		{
			return $this->unModele->getDestinationPrix(); 
		}

		public function getTransportName()
		{
			return $this->unModele->getTransportName(); 
		}

		public function getTransportPrix()
		{
			return $this->unModele->getTransportPrix(); 
		}

		public function getLogementName()
		{
			return $this->unModele->getLogementName(); 
		}

		public function getLogementPrix()
		{
			return $this->unModele->getLogementPrix(); 
		}

		public function createAccount($tab)
		{
			return $this->unModele->createAccount($tab); 
		}

		public function checkLogin($email, $mdp)
		{
			return $this->unModele->checkLogin($email, $mdp); 
		}

		public function take_reservations($reservation_id, $destination_id, $date_depart, $date_retour, $nb_personnes, $logement_id, $transport_id, $reservations_prix, $id_client, $email)
		{
			return $this->unModele->take_reservations($reservation_id, $destination_id, $date_depart, $date_retour, $nb_personnes, $logement_id, $transport_id, $reservations_prix, $id_client, $email); 
		}

		// public function test($tab)
		// {
		// 	return $this->unModele->test($tab); 
		// }

 } //fin de la classe
 
?>