    @PutMapping("/game/{id}")
    public Game updateGame(@PathVariable long id, @RequestBody Game g) {
        Optional<Game> option = gameService.get(id);
        if( option.isPresent() ) {
            g.setId(id);
            return gameService.save(g);
        } else {
            return g;
        }
    }


EXERCICES POUR MARDI
Créer le CRUD pour la table Player

╔═══════════════════════╗
║  Player              ║
╠═══════════════════════╣ 
║  id       bigint     ║
║  nickname varchar(20) ║
║  email    varchar(255)║

INSERT INTO game (title, min_players, max_players)
VALUES
  ('Doe', 'John', 30),
  ('Smith', 'Jane', 25),
  ('Brown', 'Bob', 40);