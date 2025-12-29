import Link from "next/link"

export default function ReviewsPage() {
  // Mock data for preview
  const reviews = [
    {
      id: 1,
      date: "2025-06-16",
      texte: "Excellente conférence ! Les concepts sont bien expliqués et les exemples pratiques sont très utiles.",
      stars: 5,
      conferenceId: 1,
      conferenceTitle: "Introduction à Spring Boot et Microservices",
    },
    {
      id: 2,
      date: "2025-07-23",
      texte: "Très bonne présentation sur Kubernetes. J'aurais aimé plus de temps pour les questions.",
      stars: 4,
      conferenceId: 2,
      conferenceTitle: "Architecture Cloud Native avec Kubernetes",
    },
    {
      id: 3,
      date: "2025-08-11",
      texte: "Contenu intéressant mais manque un peu de profondeur technique.",
      stars: 3,
      conferenceId: 3,
      conferenceTitle: "DevOps et CI/CD : Les bonnes pratiques",
    },
  ]

  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="bg-white shadow-sm">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <div className="flex items-center space-x-4">
              <Link href="/" className="text-blue-600 hover:text-blue-800">
                ← Retour
              </Link>
              <h1 className="text-xl font-bold text-gray-900">Reviews</h1>
            </div>
            <span className="text-sm text-gray-500">conference-service:8082</span>
          </div>
        </div>
      </nav>

      <main className="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="mb-4 rounded-lg bg-yellow-50 p-4">
            <p className="text-sm text-yellow-800">
              <strong>Mode Preview :</strong> Les reviews sont liées aux conférences via une relation OneToMany dans
              JPA.
            </p>
          </div>

          <div className="space-y-4">
            {reviews.map((review) => (
              <div key={review.id} className="rounded-lg bg-white p-6 shadow">
                <div className="mb-3 flex items-start justify-between">
                  <div className="flex-1">
                    <p className="text-sm text-gray-500">{review.date}</p>
                    <p className="mt-1 text-sm font-medium text-gray-700">{review.conferenceTitle}</p>
                  </div>
                  <div className="flex items-center space-x-1 text-lg">
                    {[...Array(5)].map((_, i) => (
                      <span key={i} className={i < review.stars ? "text-yellow-400" : "text-gray-300"}>
                        ★
                      </span>
                    ))}
                  </div>
                </div>
                <p className="text-gray-700">{review.texte}</p>
                <div className="mt-3 flex items-center justify-between">
                  <span className="text-xs text-gray-500">Conférence ID: {review.conferenceId}</span>
                  <div className="space-x-2">
                    <button className="text-xs text-blue-600 hover:text-blue-800">Modifier</button>
                    <button className="text-xs text-red-600 hover:text-red-800">Supprimer</button>
                  </div>
                </div>
              </div>
            ))}
          </div>

          <div className="mt-6 rounded-lg border border-gray-200 bg-white p-4">
            <h3 className="mb-2 font-semibold text-gray-900">Schéma de la base de données :</h3>
            <div className="space-y-1 text-sm text-gray-600">
              <div>
                <code className="rounded bg-gray-100 px-2 py-1">Review</code> - id, date, texte, stars (1-5)
              </div>
              <div>
                <code className="rounded bg-gray-100 px-2 py-1">@ManyToOne</code> - Relation avec Conference
              </div>
              <div>• Validation des stars entre 1 et 5</div>
              <div>• Cascade DELETE pour supprimer les reviews avec la conférence</div>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}
