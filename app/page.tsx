import Link from "next/link"

export default function HomePage() {
  return (
    <div className="min-h-screen bg-gray-50">
      <nav className="bg-white shadow-sm">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="flex h-16 items-center justify-between">
            <div className="flex items-center">
              <h1 className="text-xl font-bold text-gray-900">Système de Gestion de Conférences</h1>
            </div>
            <div className="flex items-center space-x-4">
              <span className="text-sm text-gray-600">Mode Preview</span>
            </div>
          </div>
        </div>
      </nav>

      <main className="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <div className="px-4 py-6 sm:px-0">
          <div className="mb-8 rounded-lg border-2 border-blue-200 bg-blue-50 p-6">
            <h2 className="mb-3 text-xl font-bold text-blue-900">📋 Architecture Microservices</h2>
            <p className="mb-2 text-sm text-blue-800">
              Ceci est un système distribué complet avec 5 microservices Spring Boot, Keycloak OAuth2, et Next.js.
            </p>
            <p className="text-sm text-blue-700">
              <strong>Pour l'utiliser :</strong> Téléchargez le projet et lancez{" "}
              <code className="rounded bg-blue-100 px-2 py-1">docker-compose up --build</code>
            </p>
            <div className="mt-4 grid grid-cols-2 gap-2 text-xs text-blue-600 md:grid-cols-3">
              <div>✓ Eureka Discovery</div>
              <div>✓ Config Server</div>
              <div>✓ API Gateway</div>
              <div>✓ OAuth2 + JWT</div>
              <div>✓ OpenFeign</div>
              <div>✓ Circuit Breaker</div>
            </div>
          </div>

          <div className="grid grid-cols-1 gap-6 md:grid-cols-2 lg:grid-cols-3">
            <Link href="/keynotes" className="group rounded-lg bg-white p-6 shadow transition-all hover:shadow-xl">
              <div className="mb-2 flex items-center justify-between">
                <h2 className="text-2xl font-bold text-gray-900">Keynotes</h2>
                <span className="text-2xl">🎤</span>
              </div>
              <p className="text-gray-600">Gestion des intervenants et keynotes speakers</p>
              <p className="mt-3 text-xs text-gray-500">
                <code className="rounded bg-gray-100 px-1">GET /api/keynotes</code>
              </p>
              <p className="mt-1 text-xs text-blue-600 opacity-0 transition-opacity group-hover:opacity-100">
                → Port 8081
              </p>
            </Link>

            <Link href="/conferences" className="group rounded-lg bg-white p-6 shadow transition-all hover:shadow-xl">
              <div className="mb-2 flex items-center justify-between">
                <h2 className="text-2xl font-bold text-gray-900">Conférences</h2>
                <span className="text-2xl">📊</span>
              </div>
              <p className="text-gray-600">Gestion des conférences et événements</p>
              <p className="mt-3 text-xs text-gray-500">
                <code className="rounded bg-gray-100 px-1">GET /api/conferences</code>
              </p>
              <p className="mt-1 text-xs text-blue-600 opacity-0 transition-opacity group-hover:opacity-100">
                → Port 8082
              </p>
            </Link>

            <Link href="/reviews" className="group rounded-lg bg-white p-6 shadow transition-all hover:shadow-xl">
              <div className="mb-2 flex items-center justify-between">
                <h2 className="text-2xl font-bold text-gray-900">Reviews</h2>
                <span className="text-2xl">⭐</span>
              </div>
              <p className="text-gray-600">Avis et évaluations des conférences</p>
              <p className="mt-3 text-xs text-gray-500">
                <code className="rounded bg-gray-100 px-1">GET /api/reviews</code>
              </p>
              <p className="mt-1 text-xs text-blue-600 opacity-0 transition-opacity group-hover:opacity-100">
                → Port 8082
              </p>
            </Link>
          </div>

          <div className="mt-8 rounded-lg bg-gradient-to-r from-purple-50 to-blue-50 p-6">
            <h3 className="mb-3 text-lg font-bold text-gray-900">🏗️ Architecture Technique</h3>
            <div className="grid gap-4 md:grid-cols-2">
              <div>
                <h4 className="mb-2 font-semibold text-gray-800">Backend Services</h4>
                <ul className="space-y-1 text-sm text-gray-700">
                  <li>• Spring Boot 3.5.9 + Spring Cloud</li>
                  <li>• Eureka Discovery (8761)</li>
                  <li>• API Gateway (8090)</li>
                  <li>• Config Server (8888)</li>
                  <li>• H2 Database (in-memory)</li>
                </ul>
              </div>
              <div>
                <h4 className="mb-2 font-semibold text-gray-800">Fonctionnalités</h4>
                <ul className="space-y-1 text-sm text-gray-700">
                  <li>• Keycloak OAuth2 + OIDC</li>
                  <li>• OpenFeign Client</li>
                  <li>• Resilience4J Circuit Breaker</li>
                  <li>• OpenAPI / Swagger UI</li>
                  <li>• Docker Compose</li>
                </ul>
              </div>
            </div>
          </div>

          <div className="mt-6 rounded-lg border border-gray-200 bg-white p-4">
            <p className="text-center text-sm text-gray-600">
              📖 Consultez <code className="rounded bg-gray-100 px-2 py-1">README.md</code> et{" "}
              <code className="rounded bg-gray-100 px-2 py-1">IMPORTANT-READ-FIRST.md</code> pour les instructions
              complètes
            </p>
          </div>
        </div>
      </main>
    </div>
  )
}
