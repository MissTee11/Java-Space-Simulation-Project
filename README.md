# Space Travel Simulation 🚀

This Java console application simulates space travel between planets using spacecraft that carry people. Each planet has its own time system, and people have limited lifespans during their journey. The simulation runs in hourly steps and updates the status of planets, spacecraft, and passengers.

## 📁 Project Structure
- `model/` – Contains the core classes (`Person`, `Spacecraft`, `Planet`, `Time`, etc.)
- `core/` – Manages the main simulation logic.
- `util/` – Utility classes for reading data from text files.
- `dist/` – Contains the runnable `.jar` file for the project.
- `Kisiler.txt`, `Araclar.txt`, `Gezegenler.txt` – Input files with data for people, spacecraft, and planets.
- `Report.pdf` – Project report describing implementation and design.
  
## ▶️ How to Run
1. Open a terminal and navigate to the project root.
2. Run the simulation using:

   ```bash
   java -jar dist/SpaceTravelSimulation.jar
