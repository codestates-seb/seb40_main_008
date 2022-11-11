'use client';
import { SessionProvider, SessionProviderProps } from 'next-auth/react';
import { unstable_getServerSession } from 'next-auth';
import React from 'react';

const SessionContainer = ({ ...props }: SessionProviderProps) => {
	return <SessionProvider {...props} />;
};

export default SessionContainer;
