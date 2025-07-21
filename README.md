# 💊 Drug Tracker App

An Android application that helps users **search for medications** using the **RxNorm API**, **track selected medications locally**, and view medication details. Built using **Java**, **MVVM**, and **Clean Architecture** with **Firebase Authentication** and **SQLite** storage.


---

## 🚀 Features

### ✅ Authentication
- Firebase Email/Password Login & Signup
- Navigation between Welcome → Signup → Login → Home

### 🔎 Medication Search
- SearchView with real-time search via **RxNorm API**
- Displays list of medications with “Add to List” button

### 💾 My Medications
- Displays locally saved medications using **SQLite**
- Swipe-to-delete functionality
- Conditional detail view navigation

### 🧾 Medication Detail
- Displays drug name, RXCUI, and placeholder data
- “Add to List” button only when opened from Search

### 🧱 Architecture
- MVVM + Clean Architecture
- Layers: `data`, `domain`, `presentation`
- Uses ViewModel, Repository, UseCases
- Retrofit, SQLite, and LiveData

---

## 🛠 Tech Stack

- **Language**: Java  
- **Architecture**: MVVM + Clean Architecture  
- **UI**: ConstraintLayout + `sdp-android`  
- **API**: [RxNorm API](https://rxnav.nlm.nih.gov/)  
- **Auth**: Firebase Authentication  
- **Database**: SQLite (via SQLiteOpenHelper)  
- **Tools**: Retrofit, Gson, Navigation Component  

---

## 📦 Folder Structure

com.himanshuhc.drugtracker
├── data
│ ├── local (SQLite helper)
│ ├── remote (API and DTO)
├── domain
│ ├── model
│ └── repository
├── presentation
│ ├── auth (Login, Signup)
│ ├── home (My Medications)
│ ├── search (SearchFragment)
│ ├── detail (MedicationDetailFragment)
│ └── MainActivity



---

## 🧪 Testing

- Core flows tested manually:
  - Search → Add to My Medications → View/Delete
  - Sign In/Out with Firebase
- Checked screen responsiveness with `sdp-android`

---

## 📥 Download APK

Latest release APK: [📦 Download here](https://github.com/hchchimanshu/Drug-Tacker/releases)

---

## 🧑‍💻 Developer

**Himanshu Chodha**  
📧 himhc397@gmail.com / himhc376@gmail.com  
📱 +91-9815668885 / +91-7696729269  
🌐 [LinkedIn]([https://linkedin.com/in/your-profile](https://www.linkedin.com/in/himanshuhc/))
