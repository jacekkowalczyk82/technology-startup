# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import sys
import logging
from datetime import datetime, timedelta

DOCUMENT_INFO=dict({})
DOCUMENT_INFO["title"]=""
DOCUMENT_INFO["author"]=""
DOCUMENT_INFO["date"]=""


deck_js_sections=dict({})


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.
    format = "%(asctime)s: %(message)s"
    logging.basicConfig(format=format, level=logging.DEBUG,
                        datefmt="%H:%M:%S")

    today = datetime.now()
    iso_date = today.isoformat()
    logging.debug(f"ISO DateTime: {iso_date}")



# Press the green button in the gutter to run the script.

def read_txt_file(file_path):
    logging.debug("read_txt_file")
    raw_string_lines = []
    with open(file_path, 'r') as txtfile:
        for line in txtfile:
            # add all lines !!!
            raw_string_lines.append(line.strip())
    return raw_string_lines


def read_markdown_file(markdown_file_path):
    logging.debug("read_markdown_file")
    raw_string_lines = []
    with open(markdown_file_path, 'r') as mdfile:
        for line in mdfile:
            # add all lines !!!
            raw_string_lines.append(line.strip())

    return raw_string_lines

def append_to_file(file_path, line):
    file = open(file_path, mode="a")
    file.write(f"{line}\n")
    file.close()

def init_empty_file(file_path):
    file = open(file_path, mode="w")
    file.write("")
    file.close()


# The input Markdown file mut contain the extra headers for example:
# % Workshop 2023-09-20
# % Jacek Kowalczyk
# % September 20, 2023
def parse_markdown_to_deck_js(markdown_string_lines):
    lines_counter=0
    section_title = ""
    section_level = ""
    md_lines = []
    deck_js_sections = []
    section_started = False
    code_fragment = False

    for line in markdown_string_lines:
        lines_counter = lines_counter + 1
        if lines_counter == 1 and line.startswith("% "):
            items = line.split("% ")
            DOCUMENT_INFO["title"] = items[1].strip()
        elif lines_counter == 2 and line.startswith("% "):
            items = line.split("% ")
            DOCUMENT_INFO["author"] = items[1].strip()
        elif lines_counter == 3 and line.startswith("% "):
            items = line.split("% ")
            DOCUMENT_INFO["date"] = items[1].strip()
        else:
            logging.debug("Parsing markdown")
            if not code_fragment and line.startswith("```"):
                logging.debug("Parsing markdown code fragment")
                code_fragment = True

            elif code_fragment and line.startswith("```"):
                code_fragment = False
                logging.debug("End of code fragment")

            if section_started and (not code_fragment) and (line.startswith("# ") or line.startswith("## ") or line.startswith("### ")) :
                logging.debug("next Section Line: " + line)

                # already started so it is previous section so we can add it to the list
                new_deck_js_section = dict({})
                new_deck_js_section["title"] = section_title
                new_deck_js_section["level"] = section_level
                new_deck_js_section["markdown_lines"] = md_lines

                deck_js_sections.append(new_deck_js_section)
                logging.debug("Previous Section closed - section_started: "+ str(section_started))
                # as it is a new section so the previous is finished
                section_started = False
                section_title = ""
                section_level = ""
                md_lines = []

            if not section_started and (not code_fragment) and (line.startswith("# ") or line.startswith("## ") or line.startswith("### ")):
                logging.debug("Section Line: " + line)

                section_started = True
                if line.startswith("# "):
                    section_level = "h1"
                else:
                    section_level = "h4"
                items = line.split("# ")
                # reset section title
                section_title = items[1].strip()
                # reset md_lines
                md_lines = []
            else:
                logging.debug("Content Line: " + line)
                md_lines.append(line)



    # section started and not finished so probably no more lines. , last section
    if section_started:
        new_deck_js_section = dict({})
        new_deck_js_section["title"] = section_title
        new_deck_js_section["level"] = section_level
        new_deck_js_section["markdown_lines"] = md_lines

        deck_js_sections.append(new_deck_js_section)

    logging.debug(DOCUMENT_INFO)
    logging.debug(deck_js_sections)

    return DOCUMENT_INFO, deck_js_sections
    pass


# <section class="slide">
#       <table align="center" class="table_title">
#         <tr><td align="center" weight="577" height="433" background="CoBa_Tower-Logo_03.jpg"></td></tr>
#         <tr><td align="right"><h4 class="center">RAPID Workshop 2023-09-20</h4></td></tr>
#         <tr><td align="right"><h4 class="center">Jacek Kowalczyk</h4></td></tr>
#         <tr><td align="right"><h4 class="center">2023-09-20</h4></td></tr>
#       </table>
#     </section>
def generate_title_slide_Coba(DOCUMENT_INFO):
    title = DOCUMENT_INFO["title"]
    author = DOCUMENT_INFO["author"]
    date = DOCUMENT_INFO["date"]

    html_tile_lines = []
    html_tile_lines.append("<section class=\"slide\">")
    html_tile_lines.append("      <table align=\"center\" class=\"table_title\">")
    html_tile_lines.append("         <tr><td align=\"center\"> <img weight=\"577\" height=\"433\" src=\"CoBa_Tower-Logo_03.jpg\"/></td></tr>")
    html_tile_lines.append(f"        <tr><td align=\"right\"><h4 class=\"center\">{title}</h4></td></tr>")
    html_tile_lines.append(f"        <tr><td align=\"right\"><h4 class=\"center\">{author}</h4></td></tr>")
    html_tile_lines.append(f"        <tr><td align=\"right\"><h4 class=\"center\">{date}</h4></td></tr>")
    html_tile_lines.append("       </table>")
    html_tile_lines.append("</section>")

    return html_tile_lines

# JacekKowalczyk82_logo

def generate_title_slide_Jacek(DOCUMENT_INFO):
    title = DOCUMENT_INFO["title"]
    author = DOCUMENT_INFO["author"]
    date = DOCUMENT_INFO["date"]

    html_tile_lines = []
    html_tile_lines.append("<section class=\"slide\">")
    html_tile_lines.append("      <table align=\"center\" class=\"table_title\">")
    html_tile_lines.append("         <tr><td align=\"center\"> <img weight=\"577\" height=\"433\" src=\"JacekKowalczyk82_logo.png\"/></td></tr>")
    html_tile_lines.append(f"        <tr><td align=\"right\"><h4 class=\"center\">{title}</h4></td></tr>")
    html_tile_lines.append(f"        <tr><td align=\"right\"><h4 class=\"center\">{author}</h4></td></tr>")
    html_tile_lines.append(f"        <tr><td align=\"right\"><h4 class=\"center\">{date}</h4></td></tr>")
    html_tile_lines.append("       </table>")
    html_tile_lines.append("</section>")

    return html_tile_lines


def init_deck_js_html(DOCUMENT_INFO, target_file_path):
    init_empty_file(target_file_path)
    header_lines = read_txt_file("deck_js_header.html.txt")

    for line in header_lines:
        append_to_file(target_file_path, line)

    append_to_file(target_file_path, "")
    append_to_file(target_file_path, "")
    append_to_file(target_file_path, "")

    # title_slide_html_lines = generate_title_slide_Coba(DOCUMENT_INFO)
    title_slide_html_lines = generate_title_slide_Jacek(DOCUMENT_INFO)
    line= ""

    for line in title_slide_html_lines:
        append_to_file(target_file_path, line)

    append_to_file(target_file_path, "")
    append_to_file(target_file_path, "")
    append_to_file(target_file_path, "")

    pass

def finish_deck_js_html(target_file_path):
    footer_lines = read_txt_file("deck_js_footer.html.txt")

    for line in footer_lines:
        append_to_file(target_file_path, line)


    pass



def write_dec_js_sections(deck_js_sections, target_file_path):
    # append_to_file(target_dir+"/output.html", line)
    for section in deck_js_sections:
        key_title = section["title"]
        section_level = section["level"]
        md_lines = section["markdown_lines"]

        html_lines = get_deck_js_section_html_for_markdown_lines(key_title, section_level, md_lines)
        for line in html_lines:
            append_to_file(target_file_path, line)

        append_to_file(target_file_path, "")
        append_to_file(target_file_path, "")
        append_to_file(target_file_path, "")

    pass

def handle_html_img(md_line):
    # ![0c214ba1e3cc70c1b434ff474ae7ec32.png](../../_resources/0c214ba1e3cc70c1b434ff474ae7ec32.png)
    # <img src="./_resources/0c214ba1e3cc70c1b434ff474ae7ec32.png" alt="0c214ba1e3cc70c1b434ff474ae7ec32.png" />
    if "![" in md_line:
        items1 = md_line.split("![")
        items2 = items1[1].split("](")
        file_name = items2[0]
        return f"<img src=\"./_resources/{file_name}\" alt=\"{file_name}\".png\" />"

    else:
        md_line
    pass


def get_table_cells(md_line):
    table_cells = md_line.split("|")
    table_size = len(table_cells)
    sub_table = table_cells[1:table_size-1] # without  the first and last items
    return sub_table


def get_html_table_for_markdown_lines(md_table_lines):
    html_table_lines = []
    # html_lines = []
    table_started = False
    table_header = False
    for md_line in md_table_lines:
        if table_started and not md_line.strip():
            #     empty line
            table_started = False
            # Let's close the table
            html_table_lines.append("</tbody>")
            html_table_lines.append("</table>")
            html_table_lines.append("")

        if not table_started and md_line.startswith("|"):
            logging.debug("parse md table")
            table_started = True
            table_header = True
            html_table_lines.append("<table class=\"normal_table\">")
            html_table_lines.append("<tbody>")

        if table_started and md_line.startswith("|"):
            logging.debug("parse md table row")
            table_cells = get_table_cells(md_line)
            if md_line.startswith("|---"):
                table_header = False
                logging.debug("parse md table end of header")
                table_cells = []
            if table_header:
                html_table_lines.append("<tr>")
                for td in table_cells:
                    html_table_lines.append(f"<th>{td}</th>")
                html_table_lines.append("</tr>")
            elif not table_header:
                if len(table_cells) > 0:
                    # regular cells
                    html_table_lines.append("<tr>")
                    for td in table_cells:
                        html_table_lines.append(f"<td>{td}</td>")
                    html_table_lines.append("</tr>")
                else:
                    html_table_lines.append("<!--  table header --> ")

    # Let's close the table
    html_table_lines.append("</tbody>")
    html_table_lines.append("</table>")
    html_table_lines.append("")

    return html_table_lines


def get_html_list_for_markdown_lines(md_list_lines):
    html_list_lines = []
    # html_lines = []
    list_started = False
    for md_line in md_list_lines:
        if list_started and not md_line.strip():
            #     empty line
            list_started = False
            # Let's close the list
            html_list_lines.append("</ul>")
            html_list_lines.append("")

        if not list_started and md_line.startswith("* "):
            logging.debug("parse md list")
            list_started = True
            html_list_lines.append("<ul>")

        if list_started and md_line.startswith("* "):
            logging.debug("parse md list row")
            html_list_lines.append("<li>")
            items = md_line.split("* ")
            html_list_lines.append(items[1])
            html_list_lines.append("</li>")
            continue


            # Let's close the list
    html_list_lines.append("</ul>")
    html_list_lines.append("")

    return html_list_lines


def get_html_pre_code_for_markdown_lines(md_code_lines):
    html_code_lines = []
    # html_lines = []
    code_started = False
    for md_line in md_code_lines:
        # if code_started and not md_line.strip():
        #     #     empty line
        #     code_started = False
        #     # Let's close the list
        #     html_code_lines.append("</pre>")
        #     html_code_lines.append("")

        if not code_started and md_line.startswith("```"):
            logging.debug("parse md code")
            code_started = True
            html_code_lines.append("<pre> ")
            continue
        elif code_started and not md_line.startswith("```"):
            #     regular code line
            logging.debug("code ")
            escaped_line = md_line.replace("<","&lt;")
            escaped_line = escaped_line.replace(">", "&gt;")
            html_code_lines.append(escaped_line)
            continue
        elif code_started and md_line.startswith("```"):
            logging.debug("end of code ")
            html_code_lines.append("</pre>")
            html_code_lines.append("")

    # # Let's close the list
    # html_code_lines.append("</pre")
    # html_code_lines.append("")

    return html_code_lines


def get_deck_js_section_html_for_markdown_lines(section_title, section_level, md_lines):
    html_section_lines = []
    logging.debug(f"parse md table {section_title}")

    html_section_lines.append("<section class=\"slide\">")
    html_section_lines.append(f"    <{section_level}>{section_title}</{section_level}>")

    md_table_lines = []
    md_list_lines = []
    md_code_lines = []

    code_fragment_started = False

    # html_lines = []
    table_started = False
    list_started = False
    for md_line in md_lines:
        if "![" in md_line:
            md_line = handle_html_img(md_line)
        # detecting tables
        if md_line.startswith("|"):
            table_started = True
            md_table_lines.append(md_line)
            continue

        elif table_started and not md_line.startswith("|"):
            table_started = False
            html_table_lines = get_html_table_for_markdown_lines(md_table_lines)
            for html in html_table_lines:
                html_section_lines.append(html)
            md_table_lines = []

        # detecting enumeration list
        if md_line.startswith("* "):
            list_started = True
            md_list_lines.append(md_line)
            continue

        elif list_started and not md_line.startswith(" * "):
            html_list_lines = get_html_list_for_markdown_lines(md_list_lines)
            for html in html_list_lines:
                html_section_lines.append(html)
            md_list_lines = []
            list_started = False

        if not code_fragment_started and md_line.startswith("```"):
            logging.debug("parse md code fragment")
            code_fragment_started = True
            md_code_lines.append(md_line)
            continue
        elif code_fragment_started and not md_line.startswith("```"):
            md_code_lines.append(md_line)
            continue
        elif code_fragment_started and md_line.startswith("```"):
            md_code_lines.append(md_line)
            code_fragment_started = False
            html_pre_code_lines = get_html_pre_code_for_markdown_lines(md_code_lines)
            for html in html_pre_code_lines:
                html_section_lines.append(html)
            md_code_lines = []
            continue
        else:
            logging.debug(f"{md_line}")
            html_section_lines.append("<p>")
            html_section_lines.append(md_line)
            html_section_lines.append("</p>")


    html_section_lines.append("</section>")

    return html_section_lines

def test_parse_markdown_list_and_code():
    test_md_lines = []
    test_md_lines.append("## Build release package more details")
    test_md_lines.append("* https://wyssmann.com/blog/2021/03/how-to-increment-versions-in-maven-builds-alternative-to-maven-release-plugin/")
    test_md_lines.append(
        "```")
    test_md_lines.append("mvn build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.incrementalVersion}")
    test_md_lines.append(" ")
    test_md_lines.append("# mvn deploy ")
    test_md_lines.append(" ")

    test_md_lines.append("docker build . -t docker-release-tag-numbers")
    test_md_lines.append("")
    test_md_lines.append("mvn build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion}-SNAPSHOT")
    test_md_lines.append("")
    test_md_lines.append("```")
    test_md_lines.append(" ")

    doc_info_header, sections = parse_markdown_to_deck_js(test_md_lines)
    print(sections)

    for section in sections:
        key_title = section["title"]
        section_level = section["level"]
        md_lines = section["markdown_lines"]

        for line in md_lines:
            print(line)

        html_lines = get_deck_js_section_html_for_markdown_lines(key_title, section_level, md_lines)
        for line in html_lines:
            print(line)

    pass



def test_parse_markdown_to_deck_js():
    test_md_lines = []
    test_md_lines.append("## Step 1")
    test_md_lines.append(" ")
    test_md_lines.append("![621b724d6e3774ab1a590c77a569fc7c.png](.. /../ _resources / 621b724d6e3774ab1a590c77a569fc7c.png)")
    test_md_lines.append(" ")
    test_md_lines.append(" ")
    test_md_lines.append("| Env | foo | bar | lolo | ittests |")
    test_md_lines.append("|----|----|----|----|-------|")
    test_md_lines.append("| DEV | 123 | 145 | 120 | 1801 |")
    test_md_lines.append("| TUC1 | 123 | 145 | 120 | 1801 |")
    test_md_lines.append("| PROD | 123 | 145 | 120 | 1801 |")
    test_md_lines.append(" ")
    test_md_lines.append(" ")
    test_md_lines.append("## Step 2")
    test_md_lines.append(" ")
    test_md_lines.append("![fobarfobar.png](.. /../ _resources / fobarfobar.png)")
    test_md_lines.append(" ")
    test_md_lines.append(" ")
    test_md_lines.append("| Env22 | foo | bar | lolo | ittests |")
    test_md_lines.append("|----|----|----|----|-------|")
    test_md_lines.append("| DEV22 | 123 | 145 | 120 | 1801 |")
    test_md_lines.append("| TUC122 | 123 | 145 | 120 | 1801 |")
    test_md_lines.append("| PROD22 | 123 | 145 | 120 | 1801 |")
    test_md_lines.append(" ")


    doc_info_header, sections = parse_markdown_to_deck_js(test_md_lines)
    print(sections)


    for section in sections:
        key_title = section["title"]
        section_level = section["level"]
        md_lines = section["markdown_lines"]

        for line in md_lines:
            print(line)

        html_lines = get_deck_js_section_html_for_markdown_lines(key_title, section_level, md_lines)
        for line in html_lines:
            print(line)

    pass



def test_html_table():
    test_md_lines = []
    # | Env | foo | bar | lolo | ittests |
    # |----|----|----|----|-------|
    # | DEV | 123 | 145 | 120 | 1801 |
    # | TUC1 | 123 | 145 | 120 | 1801 |
    # | PROD | 123 | 145 | 120 | 1801 |
    #
    test_md_lines.append("| Env | foo | bar | lolo | ittests |")
    test_md_lines.append("|----|----|----|----|-------|")
    test_md_lines.append("| DEV | 123 | 145 | 120 | 1801 |")
    test_md_lines.append("| TUC1 | 123 | 145 | 120 | 1801 |")
    test_md_lines.append("| PROD | 123 | 145 | 120 | 1801 |")
    test_md_lines.append(" ")

    html_lines = get_deck_js_section_html_for_markdown_lines("test HTML section table", "h4", test_md_lines)
    for line in html_lines:
        print(line)
    pass

if __name__ == '__main__':
    print_hi('PyCharm')

    # test_html_table(); # OK
    # test_parse_markdown_to_deck_js() # OK
    # test_parse_markdown_list_and_code(); # OK

# example params C:\dev\maintenance\docs\Presentations\deck.js\notatki-joplin\RAPID\Workshop_20230920.md \
# C:\dev\maintenance\docs\Presentations\deck.js\output_workshopy-deckjs_2023.09.20.html

# example run 
# C:\dev\install\Python310\python.exe c:\dev\utils\joplin-md-to-deck.js\joplin-md-to-deckjs.py "C:\dev\utils\joplin-md-to-deck.js\example\notatki-joplin\tools\base64 on windows.md" C:\dev\utils\joplin-md-to-deck.js\example\base64_on_windows_deckjs.html 
# example run 
# \python c:\dev\utils\joplin-md-to-deck.js\joplin-md-to-deckjs.py "./example/notatki-joplin/tools/base64 on windows.md" example/base64_on_windows_deckjs_2.html 


    if len(sys.argv) == 3:
        raw_string_lines = read_markdown_file(sys.argv[1])
        DOCUMENT_INFO, deck_js_sections = parse_markdown_to_deck_js(raw_string_lines)
        init_deck_js_html(DOCUMENT_INFO, sys.argv[2])
        write_dec_js_sections(deck_js_sections, sys.argv[2])
        finish_deck_js_html(sys.argv[2])


# See PyCharm help at https://www.jetbrains.com/help/pycharm/
