/*
* Created  by E.Maltsev
* IF-591
* */

import groovy.net.xmlrpc.XMLRPCServerProxy

String getPdfPath() {
    properties['target.path.pdf']
}

String getUsername() {
    properties['confluence.username']
}

String getPassword() {
    properties['confluence.password']
}

Boolean getIsShowHttpLog() {
    properties['confluence.log.debug'] == 'false'
}

def redirectFollowingDownload(String url) {
    while (url) {
        (url.toURL().openConnection() as HttpURLConnection).with { conn ->
            conn.addRequestProperty('Authorization', 'Basic ' + "$username:$password".bytes.encodeBase64().toString())
            conn.instanceFollowRedirects = false
            int status = conn.responseCode
            def redirect = false
            if (status != HTTP_OK) {
                if (status == HTTP_MOVED_TEMP
                        || status == HTTP_MOVED_PERM
                        || status == HTTP_SEE_OTHER)
                    redirect = true;
            }
            if (!redirect) {
                String disposition = conn.getHeaderField('Content-Disposition')
                String filename
                if (disposition) {
                    filename = disposition[(disposition.indexOf('"') + 1)..(disposition.lastIndexOf('"') - 1)]
                } else {
                    filename = url[url.lastIndexOf('/')..url.length() - 1]
                }
                new File("$pdfPath").mkdirs()
                new File("$pdfPath/$filename").withOutputStream { out ->
                    conn.inputStream.with { input ->
                        out << input
                        input.close()
                    }
                }
            }
            url = conn.getHeaderField("Location")
            if (url && url.indexOf(';') >= 0)
                url = url[0..url.indexOf(';') - 1]
        }
    }
}

String fillIn(String content) {
    String result = content
    properties.findAll { it.key.startsWith('content.') }.each {
        log.debug("PROPS=${it.key}=${it.value}")
        result = result.replace("{{${it.key[8..-1]}}}", it.value)
    }
    result
}

def runScript(def log) {
    def baseUrl = properties['confluence.baseUrl']

    def proxy = new XMLRPCServerProxy("${baseUrl}/rpc/xmlrpc", true)
    def token = proxy.'confluence2.login'(username, password)
    log.info "Login to confluence. Token=$token"

    def template = proxy.'confluence2.getPage'(token, properties['confluence.template.space'], properties['confluence.template.title'])
    log.debug "Got template: $template"

    if (!(properties['confluence.target.space'] && properties['confluence.target.parent.title'])) {
        throw new RuntimeException("Parent page should exist")
    }

    def parent
    try {
        parent = proxy.'confluence2.getPage'(token, properties['confluence.target.space'], properties['confluence.target.parent.title'])
    } catch (Exception ignored) {
        throw new RuntimeException("Parent page should exist")
    }
    log.debug "Got parent page: $parent"

    page = [
            parentId: parent.id,
            space   : properties['confluence.target.space'],
            title   : properties['confluence.target.title'],
            content : fillIn(template.content as String)
    ]

    def existingPage = null
    try {
        existingPage = proxy.'confluence2.getPage'(token, properties['confluence.target.space'], properties['confluence.target.title'])
        log.debug "Target page existed: $existingPage"
    } catch (Exception ignored) {
        log.debug("Target page not exists")
    }

    if (existingPage) {
        page.id = existingPage.id
        page.version = existingPage.version
    }

    def storedPage = proxy.'confluence2.storePage'(token, page)
    log.debug "Page stored: $storedPage"

    log.info "Downloading pdf"
    redirectFollowingDownload("${baseUrl}/spaces/flyingpdf/pdfpageexport.action?pageId=$storedPage.id")

    def success = proxy.'confluence2.logout'(token)
    log.info "Logout confluence $success"
}

System.properties.with {
    put("proxySet", "false")
    remove("http.proxyHost")
    remove("http.proxyPort")
}
Authenticator.setDefault(null);

log.info('Confluence Page Editor Start')

runScript(log)
