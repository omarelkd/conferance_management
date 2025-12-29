import Link from "next/link"

export default function KeynotesPage() {
  // Mock data for preview
  const keynotes = [
    {
      id: 1,
      nom: "Doe",
      prenom: "John",
      email: "john.doe@example.com",
      fonction: "Expert IA et Machine Learning",
    },
    {
      id: 2,
      nom: "Smith",
      prenom: "Alice",
      email: "alice.smith@example.com",
      fonction: "Architecte Cloud",
    },
    {
      id: 3,
      nom: "Martin",
      prenom: "Pierre",
      email: "pierre.martin@example.com",
      fonction: "DevOps Engineer",
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
              <h1 className="text-xl font-bold text-gray-900">Keynotes</h1>
            </div>
            <span className="text-sm text-gray-500">keynote-service:8081</span>
          </div>
        </div>
      </nav>

      <main className="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="mb-4 rounded-lg bg-yellow-50 p-4">
            <p className="text-sm text-yellow-800">
              <strong>Mode Preview :</strong> Données de démonstration. Avec Docker Compose, ces données viendront du
              microservice Spring Boot via l'API Gateway.
            </p>
          </div>

          <div className="overflow-hidden bg-white shadow sm:rounded-md">
            <ul className="divide-y divide-gray-200">
              {keynotes.map((keynote) => (
                <li key={keynote.id} className="px-6 py-4 hover:bg-gray-50">
                  <div className="flex items-center justify-between">
                    <div className="flex-1">
                      <h3 className="text-lg font-medium text-gray-900">
                        {keynote.prenom} {keynote.nom}
                      </h3>
                      <p className="mt-1 text-sm text-gray-600">{keynote.fonction}</p>
                      <p className="mt-1 text-sm text-gray-500">{keynote.email}</p>
                    </div>
                    <div className="ml-4 flex items-center space-x-2">
                      <button className="rounded bg-blue-100 px-3 py-1 text-sm text-blue-700 hover:bg-blue-200">
                        Modifier
                      </button>
                      <button className="rounded bg-red-100 px-3 py-1 text-sm text-red-700 hover:bg-red-200">
                        Supprimer
                      </button>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>

          <div className="mt-6 rounded-lg border border-gray-200 bg-white p-4">
            <h3 className="mb-2 font-semibold text-gray-900">Endpoints API disponibles :</h3>
            <div className="space-y-1 text-sm text-gray-600">
              <div>
                <code className="rounded bg-gray-100 px-2 py-1">GET /api/keynotes</code> - Liste tous les keynotes
              </div>
              <div>
                <code className="rounded bg-gray-100 px-2 py-1">POST /api/keynotes</code> - Créer un keynote
              </div>
              <div>
                <code className="rounded bg-gray-100 px-2 py-1">PUT /api/keynotes/:id</code> - Mettre à jour
              </div>
              <div>
                <code className="rounded bg-gray-100 px-2 py-1">DELETE /api/keynotes/:id</code> - Supprimer
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}
