#!/usr/bin/env python3

import filecmp
import os
import shutil
import sys

if __name__ == '__main__':
    command = ['docker-compose', 'up', '-d']
    
    current_file = os.path.join('web-api', 'Dockerfile')
    old_file = os.path.join('web-api', 'Dockerfile.old')
    
    if not os.path.exists(current_file):
        print('Error: Dockerfile does not exist!', file=sys.stderr)
        sys.exit(-1)

    if not (os.path.exists(old_file) and filecmp.cmp(current_file, old_file)):
        command.append('--build')

    command = ' '.join(command)

    if os.system(command) == 0:
        shutil.copy(current_file, old_file)
