import { NextResponse } from "next/server"
import type { NextRequest } from "next/server"

export function proxy(request: NextRequest) {
  // Since we're in preview mode without Keycloak, allow all requests
  // In production with Docker Compose, this would check auth via NextAuth
  return NextResponse.next()
}

export const config = {
  matcher: ["/((?!api|_next/static|_next/image|favicon.ico).*)"],
}
