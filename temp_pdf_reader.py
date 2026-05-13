import zlib
import re
import os

# Find the PDF
for root, dirs, files in os.walk(r"D:\项目存放"):
    for f in files:
        if f.endswith('.pdf'):
            pdf_path = os.path.join(root, f)
            print(f"FOUND: {pdf_path}")
            data = open(pdf_path, "rb").read()
            for m in re.finditer(rb"stream\s+(.*?)\s+endstream", data, re.DOTALL):
                content = m.group(1).strip()
                try:
                    decoded = zlib.decompress(content)
                    text = decoded.decode("utf-8", errors="replace")
                    if len(text) > 50:
                        print(text[:3000])
                except:
                    pass
            break
