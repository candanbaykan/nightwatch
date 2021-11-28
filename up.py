#!/usr/bin/env python3

import os

if __name__ == '__main__':
    os.system('docker-compose -f docker-compose.db.yml -f docker-compose.web.yml up -d')
