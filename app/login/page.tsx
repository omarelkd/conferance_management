import Link from "next/link"

export default function LoginPage() {
  return (
    <div className="flex min-h-screen items-center justify-center bg-gradient-to-br from-blue-50 to-purple-50">
      <div className="w-full max-w-md space-y-8 rounded-lg bg-white p-8 shadow-xl">
        <div className="text-center">
          <div className="mb-4 flex justify-center">
            <div className="rounded-full bg-blue-100 p-3">
              <span className="text-4xl">🔐</span>
            </div>
          </div>
          <h2 className="mt-6 text-3xl font-extrabold text-gray-900">Système de Gestion</h2>
          <h3 className="text-xl font-semibold text-gray-700">de Conférences</h3>
          <p className="mt-2 text-sm text-gray-600">Authentification OAuth2 via Keycloak</p>
        </div>

        <div className="mt-8 space-y-4">
          <div className="rounded-lg border-2 border-yellow-200 bg-yellow-50 p-4">
            <div className="flex">
              <span className="mr-2 text-xl">⚠️</span>
              <div>
                <p className="text-sm font-semibold text-yellow-800">Mode Preview</p>
                <p className="mt-1 text-xs text-yellow-700">
                  L'authentification Keycloak nécessite le déploiement complet avec Docker Compose.
                </p>
              </div>
            </div>
          </div>

          <Link
            href="/"
            className="flex w-full items-center justify-center rounded-md border-2 border-blue-600 bg-blue-600 px-4 py-3 text-sm font-medium text-white shadow-sm transition-colors hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
          >
            <span className="mr-2">🔑</span>
            Voir la démo (sans auth)
          </Link>

          <div className="relative">
            <div className="absolute inset-0 flex items-center">
              <div className="w-full border-t border-gray-300" />
            </div>
            <div className="relative flex justify-center text-sm">
              <span className="bg-white px-2 text-gray-500">Configuration requise</span>
            </div>
          </div>

          <div className="rounded-lg bg-gray-50 p-4 text-sm text-gray-700">
            <h4 className="mb-2 font-semibold">Pour activer l'authentification :</h4>
            <ol className="ml-4 list-decimal space-y-1 text-xs">
              <li>Lancer Keycloak avec Docker Compose</li>
              <li>Créer le realm "realm-conference"</li>
              <li>Créer le client "conference-client"</li>
              <li>Configurer les redirects URIs</li>
              <li>Créer un utilisateur test</li>
            </ol>
            <p className="mt-3 text-xs text-gray-600">
              📖 Voir <code className="rounded bg-gray-200 px-1">README.md</code> section "Configuration Keycloak"
            </p>
          </div>
        </div>

        <div className="mt-6 text-center text-xs text-gray-500">
          <p>OAuth2 + OIDC + JWT</p>
          <p className="mt-1">Port: 8080 | Realm: realm-conference</p>
        </div>
      </div>
    </div>
  )
}
