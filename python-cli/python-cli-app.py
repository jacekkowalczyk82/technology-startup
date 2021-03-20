#!/usr/local/bin/python3

# encoding: utf-8
'''

@author:     Jacek Kowalczyk

https://docs.python.org/3/library/argparse.html


'''

import sys
import os
import csv
import json

from argparse import ArgumentParser
from argparse import RawDescriptionHelpFormatter

__all__ = []
__version__ = 0.1
__date__ = '2021-03-20'
__updated__ = '2021-03-20'

SHOW_HELP = 0

DEBUG = 1

# run additional test of imported modules
TESTRUN = 0

# run with profiling by cProfile
PROFILE = 0

INFO_LEVEL=0
DEBUG_LEVEL=1
VERBOSE_LEVEL=2
VERBOSE_ON=0




class CLIError(Exception):
    '''Generic exception to raise and log different fatal errors.'''
    def __init__(self, msg):
        super(CLIError).__init__(type(self))
        self.msg = "E: %s" % msg
    def __str__(self):
        return self.msg
    def __unicode__(self):
        return self.msg



def print_verbose(log_level, text):
    if log_level == INFO_LEVEL:
        print ("INFO::" + text)
    elif log_level == DEBUG_LEVEL:
        print ("DEBUG::" + text)
    elif VERBOSE_ON > 0  and log_level >= VERBOSE_LEVEL:
        print ("VERBOSE::" + text)
    else:
        print ("OTHER::" + text)
        
    return 0 

def write_json(filepath, data_map): 
    with open(filepath, 'w') as outfile:
        json.dump(data_map, outfile, indent=4, sort_keys=True)
    outfile.close()
    return 0; 


def read_csv():
    with open(input_file, 'rt') as csvfile:
        mycsvreader = csv.reader(csvfile, delimiter=';', quotechar='"')
        for row in mycsvreader:
            print_verbose(VERBOSE_LEVEL, str(row))
            if len(row) == 0 :
                print_verbose(DEBUG_LEVEL, "Row is empty : " + str(row))
                

def write_csv(filePath, csv_data):
    with open(filePath, 'wb') as csvfile:
        mycsvwriter = csv.writer(csvfile, delimiter=';', quotechar='"')
        for row in csv_data:
            print ('row',row)
            mycsvwriter.writerow(row)
    csvfile.close()
            



    
def main(argv=None): # IGNORE:C0111
    '''Command line options.'''

    if argv is None:
        argv = sys.argv
    else:
        sys.argv.extend(argv)

    program_name = os.path.basename(sys.argv[0])
    program_version = "v%s" % __version__
    program_build_date = str(__updated__)
    program_version_message = '%%(prog)s %s (%s)' % (program_version, program_build_date)
    program_shortdesc = __import__('__main__').__doc__.split("\n")[1]
    program_license = '''%s

  Created by Jacek Kowalczyk on %s.
  Copyright 2020 Jacek Kowalczyk. All rights reserved.

  Distributed on an "AS IS" basis without warranties
  or conditions of any kind, either express or implied.

How to use it:
''' % (program_shortdesc, str(__date__))

    try:
        # Setup argument parser
        parser = ArgumentParser(description=program_license, formatter_class=RawDescriptionHelpFormatter)
        parser.add_argument("-v", "--verbose", dest="verbose", action="count", default=0, help="set verbosity level [default: %(default)s]")
        parser.add_argument('-V', '--version', action='version', version=program_version_message)
        parser.add_argument("-c", "--csv", dest="csvOutput", default=False, action="store_true", help="Use CSV Output file [default: %(default)s], otherwise use TXT format" )
        
        parser.add_argument(dest="input_file", help="Input file path")
    
        parser.add_argument(dest="output_dir", help="Output dir path")
        
        # Process arguments
        args = parser.parse_args()
        
        print ('args.verbose', args.verbose)
        
        print ('args.csvOutput', args.csvOutput)
        print ('args.input_file', args.input_file)
        print ('args.output_dir', args.output_dir)

        
        verbose = args.verbose
        print ('args.verbose', args.verbose)
        

        if verbose > 0:
            global VERBOSE_ON
            VERBOSE_ON = verbose
            print("Verbose mode on: " + str(VERBOSE_ON))
        
        

        return 0
    except KeyboardInterrupt:
        ### handle keyboard interrupt ###
        return 0
    except Exception as e:
        if DEBUG or TESTRUN:
            raise(e)
        indent = len(program_name) * " "
        sys.stderr.write(program_name + ": " + repr(e) + "\n")
        sys.stderr.write(indent + "  for help use --help")
        
        
        return 2

if __name__ == "__main__":
    if SHOW_HELP:
        sys.argv.append("-h")
        sys.argv.append("-v")
    if DEBUG:
        sys.argv.append("-v")
        
    if TESTRUN:
        import doctest
        doctest.testmod()
    if PROFILE:
        import cProfile
        import pstats
        profile_filename = '_profile.txt'
        cProfile.run('main()', profile_filename)
        statsfile = open("profile_stats.txt", "w")
        p = pstats.Stats(profile_filename, stream=statsfile)
        stats = p.strip_dirs().sort_stats('cumulative')
        stats.print_stats()
        statsfile.close()
        sys.exit(0)
    sys.exit(main())
