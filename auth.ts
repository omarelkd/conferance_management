import NextAuth from "next-auth"
import type { NextAuthConfig } from "next-auth"

// Note: Keycloak provider needs to be installed separately
// This is a placeholder configuration for preview mode
export const config = {
  providers: [],
  callbacks: {
    async jwt({ token, account }) {
      if (account) {
        token.accessToken = account.access_token
      }
      return token
    },
    async session({ session, token }) {
      session.accessToken = token.accessToken as string
      return session
    },
  },
} satisfies NextAuthConfig

export const { handlers, signIn, signOut, auth } = NextAuth(config)
