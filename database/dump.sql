-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Creato il: Mag 14, 2025 alle 10:15
-- Versione del server: 9.3.0
-- Versione PHP: 8.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pokemon_game`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `moves`
--

CREATE TABLE `moves` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `accuracy` double NOT NULL,
  `move_type` enum('DAMAGE','STATUS') NOT NULL,
  `power` double DEFAULT NULL,
  `statistic` enum('ATTACK','DEFENSE','SPEED') DEFAULT NULL,
  `percentage` double DEFAULT NULL,
  `self` tinyint(1) DEFAULT NULL
) ;

--
-- Dump dei dati per la tabella `moves`
--

INSERT INTO `moves` (`id`, `name`, `accuracy`, `move_type`, `power`, `statistic`, `percentage`, `self`) VALUES
(1, 'Flamethrower', 100, 'DAMAGE', 90, NULL, NULL, NULL),
(2, 'Surf', 100, 'DAMAGE', 90, NULL, NULL, NULL),
(3, 'Leaf Blade', 100, 'DAMAGE', 90, NULL, NULL, NULL),
(4, 'Tail Whip', 100, 'STATUS', NULL, 'DEFENSE', 10, 0),
(5, 'Fire Blast', 85, 'DAMAGE', 110, NULL, NULL, NULL),
(6, 'Power Whip', 85, 'DAMAGE', 120, NULL, NULL, NULL),
(7, 'Hydro Pump', 80, 'DAMAGE', 110, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `pokemons`
--

CREATE TABLE `pokemons` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `hp` double NOT NULL,
  `attack` double NOT NULL,
  `defense` double NOT NULL,
  `speed` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `pokemons`
--

INSERT INTO `pokemons` (`id`, `name`, `hp`, `attack`, `defense`, `speed`) VALUES
(1, 'Charizard', 100, 100, 100, 100),
(2, 'Blastoise', 100, 100, 100, 100),
(3, 'Venusaur', 100, 100, 100, 100);

-- --------------------------------------------------------

--
-- Struttura della tabella `pokemon_moves`
--

CREATE TABLE `pokemon_moves` (
  `id` int NOT NULL,
  `pokemon_id` int NOT NULL,
  `move_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dump dei dati per la tabella `pokemon_moves`
--

INSERT INTO `pokemon_moves` (`id`, `pokemon_id`, `move_id`) VALUES
(1, 1, 1),
(2, 1, 5),
(3, 1, 4),
(4, 2, 2),
(5, 2, 7),
(6, 2, 4),
(7, 3, 3),
(8, 3, 6),
(9, 3, 4);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `moves`
--
ALTER TABLE `moves`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `pokemons`
--
ALTER TABLE `pokemons`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `pokemon_moves`
--
ALTER TABLE `pokemon_moves`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pokemon_id` (`pokemon_id`),
  ADD KEY `move_id` (`move_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `moves`
--
ALTER TABLE `moves`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `pokemons`
--
ALTER TABLE `pokemons`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `pokemon_moves`
--
ALTER TABLE `pokemon_moves`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `pokemon_moves`
--
ALTER TABLE `pokemon_moves`
  ADD CONSTRAINT `pokemon_moves_ibfk_1` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemons` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `pokemon_moves_ibfk_2` FOREIGN KEY (`move_id`) REFERENCES `moves` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
