# PlayMySongs

A mini web system to upload and search music files (mp3/ogg), built with
Spring Boot 4 + Spring Data MongoDB using a service-layer architecture.
It has two modules: uploading songs and searching/playing them.

## Project structure

```
playMySongs/
├── playmysongsBack/    (backend - Spring Boot, open in IntelliJ)
├── playmysongsFront/   (frontend - HTML/JS/CSS, open in the browser)
└── musicas-teste/      (sample .mp3/.ogg files to test the upload)
```

The `musicas-teste` folder contains ready-to-use sample audio files
(three `.mp3` and one `.ogg`). Use them to test the upload when you don't
have a music file at hand.

## Requirements

- Java 25 and IntelliJ (or any way to run a Spring Boot app)
- MongoDB running locally on port 27017
- A web browser

## How to run

1. Start the MongoDB server (port 27017). On Windows, open an **Administrator**
   Command Prompt and run `net start MongoDB` (or `mongod --dbpath C:\data\db`).
2. Open the `playmysongsBack` folder in IntelliJ and run the
   `PlayMySongsApplication` class. The backend starts at http://localhost:8080
3. Open `playmysongsFront/index.html` in your browser. The home page shows the
   two modules: **Upload a song** and **Search songs**.

## How to add a song (step by step)

1. Make sure MongoDB and the backend are both running.
2. Open `index.html` and click **"Enviar uma música"** (Upload a song).
3. Pick a **style** from the combobox (the list is loaded from the backend).
4. Type the **song name** and the **artist**.
5. Click **"Escolher arquivo"** (Choose file) and select an `.mp3` or `.ogg`
   file — for example one from the `musicas-teste` folder.
6. Click **"Enviar"** (Send). On success you get a green confirmation and the
   generated file name.
7. The song is now stored. Open **"Buscar músicas"** (Search songs) and type
   part of the name, style or artist to find it.
8. (Optional) Open MongoDB Compass, refresh the connection, and check the
   `playmysongs_collection` database → `musicas` collection to see the saved
   document.

The uploaded file is saved as `name_style_artist.ext`, all lowercase, with no
spaces and no special characters (e.g. `lovemylife_pop_robbiewilliams.mp3`).

## Endpoints (backend)

| endpoint              | method | parameters                     | returns                              |
|-----------------------|--------|--------------------------------|--------------------------------------|
| apis/music-upload     | POST   | arquivo (file), nome, estilo, artista | the saved Musica (json) or an Erro |
| apis/find-musics      | GET    | chave                          | list of Musica (json) or an Erro     |
| apis/get-music-styles | GET    | none                           | list of music styles (json)          |

Tip: open `http://localhost:8080/apis/find-musics?chave=` (empty key) to list
all the songs currently stored.

## Note about playback

Uploaded files are stored in `src/main/resources/static/uploads/` (as required
by the assignment). At runtime, a newly uploaded file may only be served after
restarting the backend. If a just-uploaded song does not play, restart the
backend (or add the small config that serves this folder directly from disk).
