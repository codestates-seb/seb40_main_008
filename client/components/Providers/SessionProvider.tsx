'use client';
import { SessionProvider, SessionProviderProps } from 'next-auth/react';
import React from 'react';

const SessionContainer = ({ session, children }: SessionProviderProps) => {
	return <SessionProvider session={session}>{children}</SessionProvider>;
};

export default SessionContainer;
