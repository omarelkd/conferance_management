import Link from "next/link"

export default function ConferencesPage() {
  // Mock data for preview
  const conferences = [
    {
      id: 1,
      titre: "Introduction à Spring Boot et Microservices",
      type: "ACADEMIQUE",
      date: "2025-06-15",
      duree: 120,
      nombreInscrits: 85,
      score: 4.7,
    },
    {
      id: 2,
      titre: "Architecture Cloud Native avec Kubernetes",
      type: "COMMERCIALE",
      date: "2025-07-22",
      duree: 180,
      nombreInscrits: 120,
      score: 4.9,
    },
    {
      id: 3,
      titre: "DevOps et CI/CD : Les bonnes pratiques",
      type: "ACADEMIQUE",
      date: "2025-08-10",
      duree: 90,
      nombreInscrits: 65,
      score: 4.5,
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
              <h1 className="text-xl font-bold text-gray-900">Conférences</h1>
            </div>
            <span className="text-sm text-gray-500">conference-service:8082</span>
          </div>
        </div>
      </nav>

      <main className="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="mb-4 rounded-lg bg-yellow-50 p-4">
            <p className="text-sm text-yellow-800">
              <strong>Mode Preview :</strong> Données de démonstration. Avec Docker Compose, ce service communique avec
              keynote-service via OpenFeign.
            </p>
          </div>

          <div className="overflow-hidden bg-white shadow sm:rounded-md">
            <ul className="divide-y divide-gray-200">
              {conferences.map((conference) => (
                <li key={conference.id} className="px-6 py-4 hover:bg-gray-50">
                  <div>
                    <div className="flex items-start justify-between">
                      <h3 className="text-lg font-medium text-gray-900">{conference.titre}</h3>
                      <span
                        className={`rounded px-2 py-1 text-xs font-medium ${
                          conference.type === "ACADEMIQUE" ? "bg-blue-100 text-blue-800" : "bg-green-100 text-green-800"
                        }`}
                      >
                        {conference.type}
                      </span>
                    </div>
                    <div className="mt-3 flex flex-wrap items-center gap-4 text-sm text-gray-600">
                      <span>📅 {conference.date}</span>
                      <span>⏱️ {conference.duree} minutes</span>
                      <span>👥 {conference.nombreInscrits} inscrits</span>
                      <span className="font-medium text-yellow-600">⭐ {conference.score}/5</span>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>

          <div className="mt-6 rounded-lg border border-gray-200 bg-white p-4">
            <h3 className="mb-2 font-semibold text-gray-900">Fonctionnalités :</h3>
            <div className="space-y-1 text-sm text-gray-600">
              <div>• CRUD complet des conférences</div>
              <div>• Gestion des reviews (avis)</div>
              <div>• Communication avec keynote-service via OpenFeign</div>
              <div>• Circuit Breaker Resilience4J pour la résilience</div>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}
